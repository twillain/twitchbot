package com.motyldrogi.bot.entity.TwitchWebSocketMessage.event.eventUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.motyldrogi.bot.entity.TwitchWebSocketMessage.event.Event;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Payload {
    @JsonDeserialize(contentAs = TwitchSubscription.class)
    @JsonProperty("subscription")
    private TwitchSubscription subscription;
    
    @JsonProperty("event")
    private Event event;

    @JsonProperty("session")
    private TwitchSession session;

    
    public TwitchSession getSession() {
        return session;
    }
    public void setSession(TwitchSession session) {
        this.session = session;
    }
    public TwitchSubscription getSubscription() {
        return subscription;
    }
    public void setSubscription(TwitchSubscription subscription) {
        this.subscription = subscription;
    }
    public Event getEvent() {
        return event;
    }
    public void setEvent(Event event) {
        this.event = event;
    }
}
