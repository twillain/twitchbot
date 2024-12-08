package com.motyldrogi.bot.chatNotification.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SharedChatSubGift {
    @JsonProperty("duration_months")
    private int durationMonths;

    @JsonProperty("cumulative_total")
    private Integer cumulativeTotal;

    @JsonProperty("recipient_user_id")
    private String recipientUserId;

    @JsonProperty("recipient_user_name")
    private String recipientUserName;

    @JsonProperty("recipient_user_login")
    private String recipientUserLogin;

    @JsonProperty("sub_tier")
    private String subTier;

    @JsonProperty("community_gift_id")
    private String communityGiftId;

    public int getDurationMonths() {
        return durationMonths;
    }

    public void setDurationMonths(int durationMonths) {
        this.durationMonths = durationMonths;
    }

    public Integer getCumulativeTotal() {
        return cumulativeTotal;
    }

    public void setCumulativeTotal(Integer cumulativeTotal) {
        this.cumulativeTotal = cumulativeTotal;
    }

    public String getRecipientUserId() {
        return recipientUserId;
    }

    public void setRecipientUserId(String recipientUserId) {
        this.recipientUserId = recipientUserId;
    }

    public String getRecipientUserName() {
        return recipientUserName;
    }

    public void setRecipientUserName(String recipientUserName) {
        this.recipientUserName = recipientUserName;
    }

    public String getRecipientUserLogin() {
        return recipientUserLogin;
    }

    public void setRecipientUserLogin(String recipientUserLogin) {
        this.recipientUserLogin = recipientUserLogin;
    }

    public String getSubTier() {
        return subTier;
    }

    public void setSubTier(String subTier) {
        this.subTier = subTier;
    }

    public String getCommunityGiftId() {
        return communityGiftId;
    }

    public void setCommunityGiftId(String communityGiftId) {
        this.communityGiftId = communityGiftId;
    }

    
}

