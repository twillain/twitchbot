package com.motyldrogi.bot.poll.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PollResponseEntity {

    @JsonProperty("id")
    private String id;

    @JsonProperty("broadcaster_id")
    private String broadcasterId;

    @JsonProperty("broadcaster_name")
    private String broadcasterName;

    @JsonProperty("broadcaster_login")
    private String broadcasterLogin;

    @JsonProperty("title")
    private String title;

    @JsonProperty("choices")
    private PollChoiceEntity[] choices;

    @JsonProperty("bits_voting_enabled")
    private boolean bitsVotingEnabled;

    @JsonProperty("bits_per_vote")
    private int bitsPerVote;

    @JsonProperty("channel_points_voting_enabled")
    private boolean channelPointsVotingEnabled;

    @JsonProperty("channel_points_per_vote")
    private int channelPointsPerVote;

    @JsonProperty("status")
    private PollStatusEntity status;

    @JsonProperty("duration")
    private int duration;

    @JsonProperty("started_at")
    private String startedAt;

    @JsonProperty("ended_at")
    private String endedAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBroadcasterId() {
        return broadcasterId;
    }

    public void setBroadcasterId(String broadcasterId) {
        this.broadcasterId = broadcasterId;
    }

    public String getBroadcasterName() {
        return broadcasterName;
    }

    public void setBroadcasterName(String broadcasterName) {
        this.broadcasterName = broadcasterName;
    }

    public String getBroadcasterLogin() {
        return broadcasterLogin;
    }

    public void setBroadcasterLogin(String broadcasterLogin) {
        this.broadcasterLogin = broadcasterLogin;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public PollChoiceEntity[] getChoices() {
        return choices;
    }

    public void setChoices(PollChoiceEntity[] choices) {
        this.choices = choices;
    }

    public boolean isBitsVotingEnabled() {
        return bitsVotingEnabled;
    }

    public void setBitsVotingEnabled(boolean bitsVotingEnabled) {
        this.bitsVotingEnabled = bitsVotingEnabled;
    }

    public int getBitsPerVote() {
        return bitsPerVote;
    }

    public void setBitsPerVote(int bitsPerVote) {
        this.bitsPerVote = bitsPerVote;
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

    public PollStatusEntity getStatus() {
        return status;
    }

    public void setStatus(PollStatusEntity status) {
        this.status = status;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(String startedAt) {
        this.startedAt = startedAt;
    }

    public String getEndedAt() {
        return endedAt;
    }

    public void setEndedAt(String endedAt) {
        this.endedAt = endedAt;
    }

    public PollChoiceEntity getWinningChoice(){
        PollChoiceEntity max = null;
        for (PollChoiceEntity i : this.choices){
            PollChoiceEntity current = i;
            if (current.compareChannelPointsVoteTo(max) > 0) max = current;
        }
        return max;
    }
}
