package com.motyldrogi.bot.chatNotification.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class Emote {
    @JsonProperty("id")
    private String id;

    @JsonProperty("emote_set_id")
    private String emoteSetId;

    @JsonProperty("owner_id")
    private String ownerId;

    @JsonProperty("format")
    private List<String> format;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmoteSetId() {
        return emoteSetId;
    }

    public void setEmoteSetId(String emoteSetId) {
        this.emoteSetId = emoteSetId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public List<String> getFormat() {
        return format;
    }

    public void setFormat(List<String> format) {
        this.format = format;
    }

    
}

