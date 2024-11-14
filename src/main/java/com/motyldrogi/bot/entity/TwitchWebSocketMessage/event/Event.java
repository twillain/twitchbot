package com.motyldrogi.bot.entity.TwitchWebSocketMessage.event;

import com.fasterxml.jackson.annotation.JsonProperty;


public abstract class Event {

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("user_login")
    private String userLogin;

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("broadcaster_user_id")
    private String broadcasterUserId;

    @JsonProperty("broadcaster_user_login")
    private String broadcasterUserLogin;

    @JsonProperty("broadcaster_user_name")
    private String broadcasterUserName;

    // Getters and Setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBroadcasterUserId() {
        return broadcasterUserId;
    }

    public void setBroadcasterUserId(String broadcasterUserId) {
        this.broadcasterUserId = broadcasterUserId;
    }

    public String getBroadcasterUserLogin() {
        return broadcasterUserLogin;
    }

    public void setBroadcasterUserLogin(String broadcasterUserLogin) {
        this.broadcasterUserLogin = broadcasterUserLogin;
    }

    public String getBroadcasterUserName() {
        return broadcasterUserName;
    }

    public void setBroadcasterUserName(String broadcasterUserName) {
        this.broadcasterUserName = broadcasterUserName;
    }

}

