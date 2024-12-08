package com.motyldrogi.bot.predictions.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TopPredictorEntity {

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("user_login")
    private String userLogin;

    @JsonProperty("channel_points_used")
    private int channelPointsUsed;

    @JsonProperty("channel_points_won")
    private int channelPointsWon;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public int getChannelPointsUsed() {
        return channelPointsUsed;
    }

    public void setChannelPointsUsed(int channelPointsUsed) {
        this.channelPointsUsed = channelPointsUsed;
    }

    public int getChannelPointsWon() {
        return channelPointsWon;
    }

    public void setChannelPointsWon(int channelPointsWon) {
        this.channelPointsWon = channelPointsWon;
    }

    public int compareChannelPointsWonTo(TopPredictorEntity otherTopPredictor){
        if (otherTopPredictor == null) return 1;
        return this.channelPointsWon - otherTopPredictor.getChannelPointsWon();
    }

    
    
}
