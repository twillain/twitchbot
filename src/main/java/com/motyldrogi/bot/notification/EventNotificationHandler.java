package com.motyldrogi.bot.notification;

import com.motyldrogi.bot.entity.TwitchWebSocketMessage.event.Event;

public interface EventNotificationHandler<T extends Event> {
    public void handleNotification(T event);
}
