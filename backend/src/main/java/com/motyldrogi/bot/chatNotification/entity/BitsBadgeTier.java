package com.motyldrogi.bot.chatNotification.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BitsBadgeTier {
    @JsonProperty("tier")
    private int tier;

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    
}

