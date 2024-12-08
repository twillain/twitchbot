package com.motyldrogi.bot.chatNotification.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SharedChatCommunitySubGift {
    @JsonProperty("id")
    private String id;

    @JsonProperty("total")
    private int total;

    @JsonProperty("sub_tier")
    private String subTier;

    @JsonProperty("cumulative_total")
    private Integer cumulativeTotal;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getSubTier() {
        return subTier;
    }

    public void setSubTier(String subTier) {
        this.subTier = subTier;
    }

    public Integer getCumulativeTotal() {
        return cumulativeTotal;
    }

    public void setCumulativeTotal(Integer cumulativeTotal) {
        this.cumulativeTotal = cumulativeTotal;
    }

    
}

