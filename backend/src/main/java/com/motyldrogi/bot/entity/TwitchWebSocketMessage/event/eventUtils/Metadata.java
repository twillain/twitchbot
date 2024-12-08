package com.motyldrogi.bot.entity.TwitchWebSocketMessage.event.eventUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Metadata {

    @JsonProperty("message_id")
    private String messageId;

    @JsonProperty("message_type")
    private String messageType;

    @JsonProperty("message_timestamp")
    private String messageTimestamp;

    @JsonProperty("subscription_type")
    private String subscriptionType;
    
    @JsonProperty("subscription_version")
    private String subscriptionVersion;

    public String getMessageId() {
        return messageId;
    }
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
    public String getMessageType() {
        return messageType;
    }
    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
    public String getMessageTimestamp() {
        return messageTimestamp;
    }
    public void setMessageTimestamp(String messageTimestamp) {
        this.messageTimestamp = messageTimestamp;
    }
    public String getSubscriptionType() {
        return subscriptionType;
    }
    public void setSubscriptionType(String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }
    public String getSubscriptionVersion() {
        return subscriptionVersion;
    }
    public void setSubscriptionVersion(String subscriptionVersion) {
        this.subscriptionVersion = subscriptionVersion;
    }
}
