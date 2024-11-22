package com.motyldrogi.bot.predictions.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PredictionResponseEntity {

    @JsonProperty("id")
    private String id;

    @JsonProperty("broadcaster_id")
    private String broadcasterId;

    @JsonProperty("broadcaster_name")
    private String broadcasterName;

    @JsonProperty("broadcaster_login")
    private String broadcasterLogin;

    @JsonProperty("title")
    private String title;

    @JsonProperty("winning_outcome_id")
    private String winningOutcomeId;

    @JsonProperty("prediction_window")
    private int predictionWindow;

    @JsonProperty("status")
    private PredictionStatusEntity status;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("ended_at")
    private String endedAt;

    @JsonProperty("locked_at")
    private String lockedAt;

    @JsonProperty("outcomes")
    private PredictionOutcomeEntity[] outcomes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBroadcasterId() {
        return broadcasterId;
    }

    public void setBroadcasterId(String broadcasterId) {
        this.broadcasterId = broadcasterId;
    }

    public String getBroadcasterName() {
        return broadcasterName;
    }

    public void setBroadcasterName(String broadcasterName) {
        this.broadcasterName = broadcasterName;
    }

    public String getBroadcasterLogin() {
        return broadcasterLogin;
    }

    public void setBroadcasterLogin(String broadcasterLogin) {
        this.broadcasterLogin = broadcasterLogin;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWinningOutcomeId() {
        return winningOutcomeId;
    }

    public void setWinningOutcomeId(String winningOutcomeId) {
        this.winningOutcomeId = winningOutcomeId;
    }

    public int getPredictionWindow() {
        return predictionWindow;
    }

    public void setPredictionWindow(int predictionWindow) {
        this.predictionWindow = predictionWindow;
    }

    public PredictionStatusEntity getStatus() {
        return status;
    }

    public void setStatus(PredictionStatusEntity status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getEndedAt() {
        return endedAt;
    }

    public void setEndedAt(String endedAt) {
        this.endedAt = endedAt;
    }

    public String getLockedAt() {
        return lockedAt;
    }

    public void setLockedAt(String lockedAt) {
        this.lockedAt = lockedAt;
    }

    public PredictionOutcomeEntity[] getOutcomes() {
        return outcomes;
    }

    public void setOutcomes(PredictionOutcomeEntity[] outcomes) {
        this.outcomes = outcomes;
    }

    public PredictionOutcomeEntity getOutcomeFromTitle(String outcomeTitle) throws IllegalArgumentException {
        for (PredictionOutcomeEntity o : outcomes){
            if (o.getTitle().equalsIgnoreCase(outcomeTitle)) return o;
        }
        throw new IllegalArgumentException("No outcome with title " + outcomeTitle);
    }

    public PredictionOutcomeEntity getOutcomeFromId(String outcomeId){
        for (PredictionOutcomeEntity o : outcomes){
            if (o.getId().equalsIgnoreCase(outcomeId)) return o;
        }
        throw new IllegalArgumentException("No outcome with ID " + outcomeId);
    }

    public TopPredictorEntity getBestPredictor(String winningOutcomeId){
        PredictionOutcomeEntity outcome = getOutcomeFromId(winningOutcomeId);
        TopPredictorEntity maxPredictor = null;
        for (TopPredictorEntity p : outcome.getTopPredictors()){
            TopPredictorEntity current = p;
            if (current.compareChannelPointsWonTo(maxPredictor) > 0) maxPredictor = current;
        }
        return maxPredictor;
    }
    
}
