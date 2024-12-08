package com.motyldrogi.bot.FrontWebSocket;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.motyldrogi.bot.entity.TwitchWebSocketMessage.event.Event;



@Service
public class BroadcastService {
    
    private final SimpMessagingTemplate messagingTemplate;

    public BroadcastService(SimpMessagingTemplate messagingTemplate){
        this.messagingTemplate = messagingTemplate;
    }

    public void broadcastNotification(String topic, Event event){
        messagingTemplate.convertAndSend(topic, event);
        System.out.println("Notification broadcast ");
    }
}
