package com.motyldrogi.bot.entity.TwitchWebSocketMessage.event.eventUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Badge {

    @JsonProperty("set_id")
    private String setId;

    @JsonProperty("id")
    private String id;

    @JsonProperty("info")
    private String info;

    public String getSetId() {
        return setId;
    }

    public void setSetId(String setId) {
        this.setId = setId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    
    
}
