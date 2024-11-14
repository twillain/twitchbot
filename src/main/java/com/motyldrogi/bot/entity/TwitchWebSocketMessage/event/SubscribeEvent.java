package com.motyldrogi.bot.entity.TwitchWebSocketMessage.event;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SubscribeEvent extends Event {
    @JsonProperty("tier")
    private String tier;

    @JsonProperty("is_gift")
    private boolean isGift;
    
    public String getTier() {
        return tier;
    }
    public void setTier(String tier) {
        this.tier = tier;
    }
    public boolean isGift() {
        return isGift;
    }
    public void setGift(boolean isGift) {
        this.isGift = isGift;
    }

    
}
