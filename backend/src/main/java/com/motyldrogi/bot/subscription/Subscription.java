package com.motyldrogi.bot.subscription;

import com.motyldrogi.bot.entity.TwitchWebSocketMessage.event.Event;
import com.motyldrogi.bot.notification.EventNotificationHandler;

public class Subscription {

    private String type;

    private String version;

    private EventNotificationHandler<?> notificationHandler;

    private Class<? extends Event> eventClass;

    private String authorization;

    public Subscription(String type, String version, EventNotificationHandler<?> notificationHandler, Class<? extends Event> eventClass, String authorization) {
        this.type = type;
        this.version = version;
        this.notificationHandler = notificationHandler;
        this.eventClass = eventClass;
        this.authorization = authorization;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public EventNotificationHandler<?> getNotificationHandler() {
        return notificationHandler;
    }

    public void setNotificationHandler(EventNotificationHandler<?> notificationHandler) {
        this.notificationHandler = notificationHandler;
    }

    public Class<? extends Event> getEventClass() {
        return eventClass;
    }

    public void setEventClass(Class<? extends Event> eventClass) {
        this.eventClass = eventClass;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }
    
}
