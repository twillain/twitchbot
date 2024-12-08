package com.motyldrogi.bot.entity.TwitchWebSocketMessage.event;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StreamEvent extends Event {
    
    @JsonProperty("id")
    private String streamId;

    @JsonProperty("type")
    private String type;

    @JsonProperty("started_at")
    private String startedAt;

    public String getStreamId() {
        return streamId;
    }

    public void setStreamId(String streamId) {
        this.streamId = streamId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(String startedAt) {
        this.startedAt = startedAt;
    }

    
}
