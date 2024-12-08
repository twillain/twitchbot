package com.motyldrogi.bot.notification;

import com.motyldrogi.bot.entity.TwitchWebSocketMessage.event.Event;
import com.motyldrogi.bot.entity.TwitchWebSocketMessage.event.eventUtils.Payload;

public interface EventNotificationHandler<T extends Event> {
    public void handleNotification(Payload payload);
}
