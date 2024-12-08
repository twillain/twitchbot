package com.motyldrogi.bot.chatNotification.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SharedChatSub {
    @JsonProperty("sub_tier")
    private String subTier;

    @JsonProperty("is_prime")
    private boolean isPrime;

    @JsonProperty("duration_months")
    private int durationMonths;

    public String getSubTier() {
        return subTier;
    }

    public void setSubTier(String subTier) {
        this.subTier = subTier;
    }

    public boolean isPrime() {
        return isPrime;
    }

    public void setPrime(boolean isPrime) {
        this.isPrime = isPrime;
    }

    public int getDurationMonths() {
        return durationMonths;
    }

    public void setDurationMonths(int durationMonths) {
        this.durationMonths = durationMonths;
    }

    
}

