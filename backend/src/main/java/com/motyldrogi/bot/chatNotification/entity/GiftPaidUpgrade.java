package com.motyldrogi.bot.chatNotification.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GiftPaidUpgrade {
    @JsonProperty("gifter_is_anonymous")
    private boolean gifterIsAnonymous;

    @JsonProperty("gifter_user_id")
    private String gifterUserId;

    @JsonProperty("gifter_user_name")
    private String gifterUserName;

    public boolean isGifterIsAnonymous() {
        return gifterIsAnonymous;
    }

    public void setGifterIsAnonymous(boolean gifterIsAnonymous) {
        this.gifterIsAnonymous = gifterIsAnonymous;
    }

    public String getGifterUserId() {
        return gifterUserId;
    }

    public void setGifterUserId(String gifterUserId) {
        this.gifterUserId = gifterUserId;
    }

    public String getGifterUserName() {
        return gifterUserName;
    }

    public void setGifterUserName(String gifterUserName) {
        this.gifterUserName = gifterUserName;
    }

    
}

