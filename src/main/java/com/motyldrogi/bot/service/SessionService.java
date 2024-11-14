package com.motyldrogi.bot.service;

import org.springframework.stereotype.Service;

import com.motyldrogi.bot.entity.TwitchWebSocketMessage.event.eventUtils.TwitchSession;

@Service
public class SessionService {

    private TwitchSession session;
    private String url;

    public String getSessionId() {
        return session.getId();
    }

    public void setSessionId(String sessionId) {
        this.session.setId(sessionId);
    }

    public String getSessionStatus() {
        return session.getStatus();
    }

    public void setSessionStatus(String status) {
        this.session.setStatus(status);
    }

    public int getKeepaliveTimeoutSeconds() {
        return session.getKeepaliveTimeoutSeconds();
    }

    public void setKeepaliveTimeoutSeconds(int keepaliveTimeoutSeconds) {
        this.session.setKeepaliveTimeoutSeconds(keepaliveTimeoutSeconds);
    }

    public String getReconnectUrl() {
        return session.getReconnectUrl();
    }

    public void setReconnectUrl(String reconnectUrl) {
        this.session.setReconnectUrl(reconnectUrl);
    }

    public String getConnectedAt() {
        return session.getConnectedAt();
    }

    public void setConnectedAt(String connectedAt) {
        this.session.setConnectedAt(connectedAt);
    }

    public TwitchSession getSession() {
        return session;
    }

    public void setSession(TwitchSession session) {
        this.session = session;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
