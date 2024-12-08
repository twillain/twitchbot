package com.motyldrogi.bot.entity.TwitchWebSocketMessage;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.motyldrogi.bot.entity.TwitchWebSocketMessage.event.eventUtils.Metadata;
import com.motyldrogi.bot.entity.TwitchWebSocketMessage.event.eventUtils.Payload;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TwitchWebSocketMessage {

    @JsonProperty("metadata")
    private Metadata metadata;

    @JsonProperty("payload")
    private Payload payload;

    public Metadata getMetadata() {
        return metadata;
    }
    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }
    public Payload getPayload() {
        return payload;
    }
    public void setPayload(Payload payload) {
        this.payload = payload;
    }
}
