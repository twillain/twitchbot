package com.motyldrogi.bot.entity.TwitchWebSocketMessage.event.eventUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Prediction {
    @JsonProperty("broadcaster_id")
    private String broadcasterId;
    @JsonProperty("title")
    private String title;
    @JsonProperty("outcomes")
    private PredictionOutcome[] outcomes;
    @JsonProperty("prediction_window")
    private int predictionWindow;

    public Prediction(String broadcasterId, String title, String[] outcomes, int predictionWindow) {
        this.broadcasterId = broadcasterId;
        this.title = title;
        this.predictionWindow = predictionWindow;
        this.outcomes = new PredictionOutcome[outcomes.length];
        for (int i=0 ; i<outcomes.length ; i++) {
            this.outcomes[i] = new PredictionOutcome(outcomes[i]);
        }
    }

    public String getBroadcasterId() {
        return broadcasterId;
    }

    public void setBroadcasterId(String broadcasterId) {
        this.broadcasterId = broadcasterId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public PredictionOutcome[] getOutcomes() {
        return outcomes;
    }

    public void setOutcomes(PredictionOutcome[] outcomes) {
        this.outcomes = outcomes;
    }

    public int getPredictionWindow() {
        return predictionWindow;
    }

    public void setPredictionWindow(int predictionWindow) {
        this.predictionWindow = predictionWindow;
    }
        
}
