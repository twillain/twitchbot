package com.motyldrogi.bot.chatNotification.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SharedChatResub {
    @JsonProperty("cumulative_months")
    private int cumulativeMonths;

    @JsonProperty("duration_months")
    private int durationMonths;

    @JsonProperty("streak_months")
    private int streakMonths;

    @JsonProperty("sub_tier")
    private String subTier;

    @JsonProperty("is_prime")
    private boolean isPrime;

    @JsonProperty("is_gift")
    private boolean isGift;

    @JsonProperty("gifter_is_anonymous")
    private boolean gifterIsAnonymous;

    @JsonProperty("gifter_user_id")
    private String gifterUserId;

    @JsonProperty("gifter_user_name")
    private String gifterUserName;

    @JsonProperty("gifter_user_login")
    private String gifterUserLogin;

    public int getCumulativeMonths() {
        return cumulativeMonths;
    }

    public void setCumulativeMonths(int cumulativeMonths) {
        this.cumulativeMonths = cumulativeMonths;
    }

    public int getDurationMonths() {
        return durationMonths;
    }

    public void setDurationMonths(int durationMonths) {
        this.durationMonths = durationMonths;
    }

    public int getStreakMonths() {
        return streakMonths;
    }

    public void setStreakMonths(int streakMonths) {
        this.streakMonths = streakMonths;
    }

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

    public boolean isGift() {
        return isGift;
    }

    public void setGift(boolean isGift) {
        this.isGift = isGift;
    }

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

    public String getGifterUserLogin() {
        return gifterUserLogin;
    }

    public void setGifterUserLogin(String gifterUserLogin) {
        this.gifterUserLogin = gifterUserLogin;
    }

    
}

