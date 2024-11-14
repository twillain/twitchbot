package com.motyldrogi.bot.entity.TwitchWebSocketMessage.event;

import com.motyldrogi.bot.entity.TwitchWebSocketMessage.event.eventUtils.TwitchSession;

public class ReconnectEvent extends Event {
    private TwitchSession session;

    public TwitchSession getSession() {
        return session;
    }

    public void setSession(TwitchSession session) {
        this.session = session;
    }
}
