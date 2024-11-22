package com.motyldrogi.bot.poll.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PollChoiceEntity {

    @JsonProperty("id")
    private String id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("votes")
    private int votes;

    @JsonProperty("channel_points_votes")
    private int channelPointsVotes;

    @JsonProperty("bits_votes")
    private int bitsVotes;

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

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public int getChannelPointsVotes() {
        return channelPointsVotes;
    }

    public void setChannelPointsVotes(int channelPointsVotes) {
        this.channelPointsVotes = channelPointsVotes;
    }

    public int getBitsVotes() {
        return bitsVotes;
    }

    public void setBitsVotes(int bitsVotes) {
        this.bitsVotes = bitsVotes;
    }

    public int compareChannelPointsVoteTo(PollChoiceEntity otherChoice){
        if (otherChoice == null) return 1;
        return this.channelPointsVotes - otherChoice.getChannelPointsVotes();
    }
    
}
