package com.motyldrogi.bot.subscription;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.motyldrogi.bot.TwitchApi.TwitchApiService;
import com.motyldrogi.bot.TwitchWebSocket.TwitchWebSocketSessionService;
import com.motyldrogi.bot.configuration.AppProperties;

@Service
public class SubscriptionRegisterService {

    private final TwitchApiService twitchApiService;
    private final TwitchWebSocketSessionService sessionService;
    private final AppProperties properties;
    private SubscriptionRegistry subscriptionRegistry;
    private Map<String, Subscription> subscriptions = new HashMap<>();

    private String url = "https://api.twitch.tv/helix/eventsub/subscriptions";
    
    public SubscriptionRegisterService(
        TwitchApiService twitchApiService,
        SubscriptionRegistry subscriptionRegistry,
        TwitchWebSocketSessionService sessionService,
        AppProperties properties
    ){
        this.twitchApiService = twitchApiService;
        this.subscriptionRegistry = subscriptionRegistry;
        this.sessionService = sessionService;
        this.properties = properties;
    }

    public void addImplementedSubscriptions(String... types){
        for (String type : types){
            Subscription subscription = subscriptionRegistry.getSubscription(type);
            if (subscription != null){
                subscriptions.put(type, subscription);
                System.out.println("Added subscription : " + type);
            } else {
                System.err.println("No implementation found for type : " + type);
            }
        }
    }

    public void addAllImplementedSubscriptions(){
        subscriptionRegistry.getAllSubscriptions().forEach( (type, subscription) -> {
            subscriptions.put(type, subscription);
        });
    }

    public void removeSubscription(String type){
        subscriptions.remove(type);
    }

    public Subscription getSubscription(String type){
        return subscriptions.get(type);
    }

    public Map<String, Subscription> getAllSubscriptions(){
        return subscriptions;
    }

    public List<String> getAllImplementedSubscriptionTypes(){
        return subscriptionRegistry.getSubscriptionTypes();
    }

    public List<String> getAllRegisteredSubscriptionTypes(){
        return subscriptions.keySet().stream().toList();
    }

    public List<String> getAllImplementedSubscriptionAuthorization(){
        return subscriptionRegistry.getSubscriptionAuthorization();
    }

    public List<String> getAllRegisteredSubscriptionAuthorization(){
        return subscriptions.values().stream().map(Subscription::getAuthorization).toList();
    }

    public void removeAllSubscriptions(){
        subscriptions = new HashMap<>();
    }

    public void registerToSubscriptions(){
        subscriptions.forEach( (type, subscription) -> {
            subscribeToEvent(type, subscription.getVersion());
            System.out.println("Subscribed to : " + type);
        });
            
    }

    public void registerToAllImplementedSubscriptions(){
        subscriptionRegistry.getAllSubscriptions().forEach( (type, subscription) -> {
            subscribeToEvent(type, subscription.getVersion());
        });
    }

    /**
     * @Post Subscribe to a Twitch event. You must subscribe to an event to receive notifications
     * @info https://dev.twitch.tv/docs/eventsub/eventsub-subscription-types/
     * @param eventSubscriptionCode The code of the event to subscribe to (channel.follow, channel.subscribe, channel.chat.message)
     * @param version The version of the event
     * @return The response of the POST request
     */
    public void subscribeToEvent(String eventSubscriptionCode, String version){
        String body = String.format("""
            {
                "type": "%s",
                "version": "%s",
                "condition": {
                    "user_id": "%s",
                    "broadcaster_user_id": "%s",
                    "moderator_user_id": "%s"
                },
                "transport": {
                    "method": "websocket",
                    "session_id": "%s"
                }
            }
            """,
            eventSubscriptionCode,
            version,
            properties.getUserId(),
            properties.getBroadcasterUserId(),
            properties.getBroadcasterUserId(),
            sessionService.getSessionId()
            );
        ResponseEntity<String> response = twitchApiService.sendPostRequest(url, body);
        System.out.println("Response for Subscription " + eventSubscriptionCode + " : " + response.getStatusCode());
        
    }

}
