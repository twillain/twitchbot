package com.motyldrogi.bot.entity.TwitchWebSocketMessage.event.eventUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Poll {
    @JsonProperty("broadcaster_id")
    private String broadcasterId;

    @JsonProperty("title")
    private String title;
    
    @JsonProperty("choices")
    private PredictionOutcome[] choices;

    @JsonProperty("channel_points_voting_enabled")
    private boolean channelPointsVotingEnabled;

    @JsonProperty("channel_points_per_vote")
    private int channelPointsPerVote;

    @JsonProperty("duration")
    private int duration;

    public Poll(String broadcasterId, String title, String[] choices, boolean channelPointsVotingEnabled, int channelPointsPerVote, int duration) {
        this.broadcasterId = broadcasterId;
        this.title = title;
        this.channelPointsVotingEnabled = channelPointsVotingEnabled;
        if (channelPointsVotingEnabled) this.channelPointsPerVote = channelPointsPerVote;
        this.duration = duration;
        this.choices = new PredictionOutcome[choices.length];
        for (int i=0 ; i<choices.length ; i++) {
            this.choices[i] = new PredictionOutcome(choices[i]);
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

    public PredictionOutcome[] getChoices() {
        return choices;
    }

    public void setChoices(PredictionOutcome[] choices) {
        this.choices = choices;
    }

    public boolean isChannelPointsVotingEnabled() {
        return channelPointsVotingEnabled;
    }

    public void setChannelPointsVotingEnabled(boolean channelPointsVotingEnabled) {
        this.channelPointsVotingEnabled = channelPointsVotingEnabled;
    }

    public int getChannelPointsPerVote() {
        return channelPointsPerVote;
    }

    public void setChannelPointsPerVote(int channelPointsPerVote) {
        this.channelPointsPerVote = channelPointsPerVote;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    
    
}
