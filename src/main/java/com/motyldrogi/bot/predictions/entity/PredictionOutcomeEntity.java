package com.motyldrogi.bot.predictions.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PredictionOutcomeEntity {
    
    @JsonProperty
    private String id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("users")
    private int users;

    @JsonProperty("channel_points")
    private int channelPoints;

    @JsonProperty("color")
    private String color;

    @JsonProperty("top_predictors")
    private TopPredictorEntity[] topPredictors;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUsers() {
        return users;
    }

    public void setUsers(int users) {
        this.users = users;
    }

    public int getChannelPoints() {
        return channelPoints;
    }

    public void setChannelPoints(int channelPoints) {
        this.channelPoints = channelPoints;
    }

    public String getColor(){
        return this.color;
    }

    public void setColor(String color){
        this.color = color;
    }

    public TopPredictorEntity[] getTopPredictors() {
        return topPredictors;
    }

    public void setTopPredictors(TopPredictorEntity[] topPredictors) {
        this.topPredictors = topPredictors;
    }

    
}
