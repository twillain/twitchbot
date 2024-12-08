package com.motyldrogi.bot.notification;

import org.springframework.stereotype.Service;

import com.motyldrogi.bot.entity.TwitchWebSocketMessage.event.StreamEvent;
import com.motyldrogi.bot.entity.TwitchWebSocketMessage.event.eventUtils.Payload;
import com.motyldrogi.bot.stream.entity.StreamService;

@Service
public class StreamNotificationHandler implements EventNotificationHandler<StreamEvent> {

    private final StreamService streamService;

    public StreamNotificationHandler(StreamService streamService){
        this.streamService = streamService;
    }

    @Override
    public void handleNotification(Payload payload) {
        switch (payload.getSubscription().getType()){
            case "stream.online":
                handleStreamOnlineSubscription(payload);
                break;
            case "stream.offline":
                handleStreamOfflineSubscription(payload);
                break;
            default:
                break;
        }
    }

    private void handleStreamOnlineSubscription(Payload payload){
        StreamEvent event = (StreamEvent) payload.getEvent();
        streamService.setLive(event);
    }

    private void handleStreamOfflineSubscription(Payload payload){
        streamService.setOffline();
    }
    
}
