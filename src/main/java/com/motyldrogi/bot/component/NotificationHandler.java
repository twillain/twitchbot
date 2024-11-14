package com.motyldrogi.bot.component;

import java.io.IOException;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.motyldrogi.bot.entity.TwitchWebSocketMessage.event.Event;
import com.motyldrogi.bot.notification.*;
import com.motyldrogi.bot.subscription.SubscriptionRegisterService;


@Component
public class NotificationHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final SubscriptionRegisterService subscriptionRegisterService;

    public NotificationHandler(SubscriptionRegisterService subscriptionRegisterService){
        this.subscriptionRegisterService = subscriptionRegisterService;
    }


        public <T extends Event> void handleNotification(JsonNode rootNode){
            try{
                
                String eventType = rootNode.path("payload").path("subscription").path("type").asText();
                System.out.println("Event type : " + eventType);

                @SuppressWarnings("unchecked")
                EventNotificationHandler<T> handler = (EventNotificationHandler<T>) subscriptionRegisterService.getSubscription(eventType).getNotificationHandler();

                if (handler != null){
                    handler.handleNotification(parseEvent(rootNode, eventType));
                } else {
                    System.err.println("No handler found for event type : " + eventType);
                }

            } catch (IOException e){
                System.err.println(e.getMessage());
            }
        }

        public <T extends Event> T parseEvent(JsonNode rootNode, String eventType) throws IOException{
            
            @SuppressWarnings("unchecked")
            Class<T> eventClass = (Class<T>) subscriptionRegisterService.getSubscription(eventType).getEventClass();

            if (eventClass == null){
                throw new IllegalArgumentException("No event class found for event type : " + eventType);
            }

            return objectMapper.readValue(objectMapper.writeValueAsString(rootNode.path("payload").path("event")), eventClass);
        }

}
