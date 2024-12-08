package com.motyldrogi.bot.chatNotification.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Fragment {
    @JsonProperty("type")
    private String type;

    @JsonProperty("text")
    private String text;

    @JsonProperty("cheermote")
    private Cheermote cheermote;

    @JsonProperty("emote")
    private Emote emote;

    @JsonProperty("mention")
    private Mention mention;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Cheermote getCheermote() {
        return cheermote;
    }

    public void setCheermote(Cheermote cheermote) {
        this.cheermote = cheermote;
    }

    public Emote getEmote() {
        return emote;
    }

    public void setEmote(Emote emote) {
        this.emote = emote;
    }

    public Mention getMention() {
        return mention;
    }

    public void setMention(Mention mention) {
        this.mention = mention;
    }

    
}
