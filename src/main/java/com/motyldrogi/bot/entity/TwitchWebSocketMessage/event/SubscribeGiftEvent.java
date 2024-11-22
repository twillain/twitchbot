package com.motyldrogi.bot.entity.TwitchWebSocketMessage.event;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SubscribeGiftEvent extends Event {

    @JsonProperty("tier")
    private String tier;

    @JsonProperty("is_anonymous")
    private int total;

    @JsonProperty("cumulative_total")
    private int cumulativeTotal;

    @JsonProperty("is_anonymous")
    private boolean isAnonymous;

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCumulativeTotal() {
        return cumulativeTotal;
    }

    public void setCumulativeTotal(int cumulativeTotal) {
        this.cumulativeTotal = cumulativeTotal;
    }

    public boolean isAnonymous() {
        return isAnonymous;
    }

    public void setAnonymous(boolean isAnonymous) {
        this.isAnonymous = isAnonymous;
    }

}
