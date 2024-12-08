package com.motyldrogi.bot.entity.TwitchWebSocketMessage.event.eventUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TwitchSession {

    @JsonProperty("id")
    private String id;

    @JsonProperty("status")
    private String status;

    @JsonProperty("keepalive_timeout_seconds")
    private int keepaliveTimeoutSeconds;

    @JsonProperty("reconnect_url")
    private String reconnectUrl;

    @JsonProperty("connected_at")
    private String connectedAt;

    @JsonProperty("recovery_url")
    private String recovery_url;

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

    public int getKeepaliveTimeoutSeconds() {
        return keepaliveTimeoutSeconds;
    }

    public void setKeepaliveTimeoutSeconds(int keepaliveTimeoutSeconds) {
        this.keepaliveTimeoutSeconds = keepaliveTimeoutSeconds;
    }

    public String getReconnectUrl() {
        return reconnectUrl;
    }

    public void setReconnectUrl(String reconnectUrl) {
        this.reconnectUrl = reconnectUrl;
    }

    public String getConnectedAt() {
        return connectedAt;
    }

    public void setConnectedAt(String connectedAt) {
        this.connectedAt = connectedAt;
    }

    public String getRecovery_url() {
        return recovery_url;
    }

    public void setRecovery_url(String recovery_url) {
        this.recovery_url = recovery_url;
    }
}
