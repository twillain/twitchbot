package com.motyldrogi.bot.stream.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StreamEntity {

    @JsonProperty("id")
    private String streamId;
    
    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("user_login")
    private String userLogin;

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("game_id")
    private String gameId;

    @JsonProperty("game_name")
    private String gameName;

    @JsonProperty("type")
    private String type;

    @JsonProperty("title")
    private String title;

    @JsonProperty("tags")
    private String[] tags;

    @JsonProperty("viewer_count")
    private int viewerCount;

    @JsonProperty("started_at")
    private String startedAt;

    @JsonProperty("language")
    private String language;

    @JsonProperty("thumbnail_url")
    private String thumbnailUrl;

    /*Deprecated */
    //private String[] tag_ids;

    @JsonProperty("is_mature")
    private boolean isMature;

    public String getStreamId() {
        return streamId;
    }

    public void setStreamId(String streamId) {
        this.streamId = streamId;
    }

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

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public int getViewerCount() {
        return viewerCount;
    }

    public void setViewerCount(int viewerCount) {
        this.viewerCount = viewerCount;
    }

    public String getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(String startedAt) {
        this.startedAt = startedAt;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public boolean isMature() {
        return isMature;
    }

    public void setMature(boolean isMature) {
        this.isMature = isMature;
    }

    

}
