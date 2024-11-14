package com.motyldrogi.bot.entity.TwitchWebSocketMessage;

public class TwitchEventVerification extends TwitchWebSocketMessage {

    private String challenge;

    public String getChallenge() {
        return challenge;
    }

    public void setChallenge(String challenge) {
        this.challenge = challenge;
    }

}
