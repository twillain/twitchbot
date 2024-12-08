package com.motyldrogi.bot.entity.TwitchWebSocketMessage.event;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FollowEvent extends Event {
    
    @JsonProperty("followed_at")
    private String followedAt;

    public String getFollowedAt() {
        return followedAt;
    }

    public void setFollowedAt(String followedAt) {
        this.followedAt = followedAt;
    }
}
