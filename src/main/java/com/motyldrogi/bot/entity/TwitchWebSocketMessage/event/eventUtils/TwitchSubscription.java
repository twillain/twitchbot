package com.motyldrogi.bot.entity.TwitchWebSocketMessage.event.eventUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TwitchSubscription {
    @JsonProperty("id")
    private String id;

    @JsonProperty("status")
    private String status;

    @JsonProperty("type")
    private String type;

    @JsonProperty("version")
    private String version;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("cost")
    private int cost;

    @JsonProperty("condition")
    private Condition condition;

    @JsonProperty("transport")
    private Transport transport;

    public static class Condition {
        @JsonProperty("broadcaster_user_id")
        private String broadcasterUserId;

        @JsonProperty("user_id")
        private String userId;

        @JsonProperty("moderator_user_id")
        private String moderatorUserId;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getBroadcasterUserId() {
            return broadcasterUserId;
        }

        public void setBroadcasterUserId(String broadcasterUserId) {
            this.broadcasterUserId = broadcasterUserId;
        }

        public String getModeratorUserId(){
            return this.moderatorUserId;
        }

        public void setModeratorUserId(String moderatorUserId){
            this.moderatorUserId = moderatorUserId;
        }
    }

    public static class Transport {
        @JsonProperty("method")
        private String method;

        @JsonProperty("callback")
        private String callback;

        @JsonProperty("secret")
        private String secret;

        @JsonProperty("session_id")
        private String sessionId;

        public String getSessionId() {
            return sessionId;
        }

        public void setSessionId(String sessionId) {
            this.sessionId = sessionId;
        }

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }

        public String getCallback() {
            return callback;
        }

        public void setCallback(String callback) {
            this.callback = callback;
        }

        public String getSecret() {
            return secret;
        }

        public void setSecret(String secret) {
            this.secret = secret;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    

}
