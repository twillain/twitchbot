package com.motyldrogi.bot.chatNotification.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PrimePaidUpgrade {
    @JsonProperty("sub_tier")
    private String subTier;

    public String getSubTier() {
        return subTier;
    }

    public void setSubTier(String subTier) {
        this.subTier = subTier;
    }

    
}

