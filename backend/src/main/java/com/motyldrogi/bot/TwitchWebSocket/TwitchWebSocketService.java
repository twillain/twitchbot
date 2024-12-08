package com.motyldrogi.bot.TwitchWebSocket;


import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.motyldrogi.bot.component.NotificationHandler;
import com.motyldrogi.bot.entity.TwitchWebSocketMessage.event.eventUtils.TwitchSession;
import com.motyldrogi.bot.subscription.SubscriptionRegisterService;


@ClientEndpoint
public class TwitchWebSocketService {
    
    private static final String TWITCH_WEBSOCKET_URL = "wss://eventsub.wss.twitch.tv/ws";

    private TwitchWebSocketSessionService sessionService;
    private final NotificationHandler notificationHandler;
    private final SubscriptionRegisterService subscriptionRegisterService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private Map<String, Session> sessions = new HashMap<>();

    private String currentSessionUrl;

    public TwitchWebSocketService(
        TwitchWebSocketSessionService sessionService,
        SubscriptionRegisterService subscriptionRegisterService,
        NotificationHandler notificationHandler
        ) {
        this.sessionService = sessionService;
        this.notificationHandler = notificationHandler;
        this.subscriptionRegisterService = subscriptionRegisterService;
    }

    public void connectWebSocket() {
        Session session = connectWebSocketToURL(TWITCH_WEBSOCKET_URL);
        if (session != null) {
            sessions.put(TWITCH_WEBSOCKET_URL, session);
            currentSessionUrl = TWITCH_WEBSOCKET_URL;
        } else {
            System.err.println("Error connecting to Twitch Websocket");
        }
    }

    public Session connectWebSocketToURL(String url){
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            
            URI uri = URI.create(url);
            Session session = container.connectToServer(this, uri);
            
            if (session == null) {
                System.err.println("Session is null");
            }
            System.err.println("WebSocket connection etablished.");
            return session;
        } catch (IOException e) {
            System.err.println("Error connecting to Twitch Websocket: " + e.getMessage());
        } catch (DeploymentException e){
            System.err.println("Error deploying Twitch Websocket: " + e.getMessage());
        }
        return null;
    }

    @OnOpen
    public void onOpen(Session session){
        System.out.println("Connected to Twitch Websocket");
    }

    @OnError
    public void onError(Session session, Throwable throwable){
        System.err.println("Error on session: " + throwable.getMessage());
        throwable.printStackTrace();
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason){
        System.out.println("Session closed: " + closeReason.getReasonPhrase());

    }

    @OnMessage
    public void onMessage(String message){
        try{
            
            JsonNode rootNode = objectMapper.readTree(message);

            String messageType = rootNode.path("metadata").path("message_type").asText();

            System.out.println("Message type: " + messageType);
            
            switch (messageType) {
                // Handle Session Handshake
                case "session_welcome":
                    sessionHandshakeHandler(rootNode);
                    break;

                // Handle revocation
                case "revocation":
                    sessionRevocationHandler(rootNode);
                    break;

                case "session_keepalive":
                    sessionKeepAliveHandler(rootNode);
                    break;

                // Handle notification
                case "notification":
                    sessionNotificationHandler(rootNode);
                    break;

                // Handle reconnection
                case "session_reconnect":
                    sessionReconnectionHandler(rootNode);
                    break;

                // Handle notification:
                default:
                    sessionUnkownTypeHandler(rootNode);
                    break;
                   
            }
        } catch (IOException e){
            System.err.println("Error handling message : " + e.getMessage());
        } 
    }

    private void sessionHandshakeHandler(JsonNode rootNode) throws IOException {
        String session = objectMapper.writeValueAsString(rootNode.path("payload").path("session"));
        TwitchSession twitchSession = objectMapper.readValue(session, TwitchSession.class);
        
        if (sessionService.getSession() != null && sessionService.getSessionStatus().equals("reconnecting")){

            try {
                String previousUrl = sessionService.getUrl();
                Session previousSession = sessions.get(previousUrl);
                if (previousSession != null){
                    previousSession.close();
                    sessions.remove(previousUrl);
                } 
            } catch (IOException e){
                System.err.println("Error closing previous session: " + e.getMessage());
            }
            currentSessionUrl = sessionService.getReconnectUrl();
            sessions.get(currentSessionUrl).setMaxIdleTimeout(twitchSession.getKeepaliveTimeoutSeconds() * 1000);
            sessionService.setUrl(currentSessionUrl);
            sessionService.setSession(twitchSession);
            return;
        }
        sessionService.setUrl(currentSessionUrl);
        sessionService.setSession(twitchSession);
        subscribeToEvents();
    }

    private void sessionRevocationHandler(JsonNode rootNode) {
        //TODO Handle revocation
        System.err.println("Revocation received");
    }

    private void sessionKeepAliveHandler(JsonNode rootNode) {
        //TODO Handle keepalive
        System.err.println("Session keepalive received");
    }

    private void sessionNotificationHandler(JsonNode rootNode){
        notificationHandler.handleNotification(rootNode);
    }

    private void sessionReconnectionHandler(JsonNode rootNode) throws IOException {
        //TODO Check timestamp to get latest reconnect notification
        String reconnectingSession = objectMapper.writeValueAsString(rootNode.path("payload").path("session"));
        TwitchSession reconnectingTwitchSession = objectMapper.readValue(reconnectingSession, TwitchSession.class);

        Session newSession = connectWebSocketToURL(reconnectingTwitchSession.getReconnectUrl());
        if (newSession != null){
            sessions.put(reconnectingTwitchSession.getReconnectUrl(), newSession);
            sessionService.setSessionStatus("reconnecting");
        } else {
            System.err.println("Error reconnecting to Twitch Websocket");
        }
    }

    private void sessionUnkownTypeHandler(JsonNode rootNode){
        System.err.println("Unknown message type: " + rootNode.path("metadata").path("message_type").asText());
    }

    private void subscribeToEvents(){
        subscriptionRegisterService.registerToSubscriptions();
    }

}
