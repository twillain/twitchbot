package com.motyldrogi.bot.chatNotification.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SharedChatAnnouncement {
    @JsonProperty("color")
    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    
}

