package com.motyldrogi.bot.entity.TwitchWebSocketMessage.event.eventUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PredictionOutcome {

    @JsonProperty("title")
    String title;

    public PredictionOutcome(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    
    
}
