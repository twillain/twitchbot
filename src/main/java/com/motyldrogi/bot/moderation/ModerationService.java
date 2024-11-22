package com.motyldrogi.bot.moderation;

import org.springframework.http.ResponseEntity;

import com.motyldrogi.bot.configuration.AppProperties;
import com.motyldrogi.bot.service.TwitchApiService;

public class ModerationService {

    private final TwitchApiService twitchApiService;
    private final AppProperties properties;
    private final String url = "https://api.twitch.tv/helix/moderation/bans";

    public ModerationService(
        TwitchApiService twitchApiService,
        AppProperties properties
    ) {
        this.twitchApiService = twitchApiService;
        this.properties = properties;
    }

    /**
     * @Post Ban a user from the Twitch channel
     * 
     * @Pre The user must be a moderator of the channel
     * 
     * @param userId The id of the user to ban
     * @param reason The reason of the ban
     * 
     * @return The response of the POST request
     */
    public void banUser(String userId, String reason){
        String body = String.format("{\"broadcaster_id\": \"%s\", \"moderator_id\": \"%s\", \"data\": {\"user_id\": \"%s\", \"reason\":\"%s\"}}",
                properties.getBroadcasterUserId(), properties.getUserId(), userId, reason);
        
        ResponseEntity<String> response = twitchApiService.sendPostRequest(url, body);
    }

    /**
     * @Post Time out a user from the Twitch channel
     * 
     * @Pre The user must be a moderator of the channel
     * 
     * @param userId The id of the user to time out
     * @param duration The duration of the time out
     * @param reason The reason of the time out
     * 
     * @return The response of the POST request
     * 
     */
    public void timeOutUser(String userId, String duration, String reason){
        String body = String.format("{\"broadcaster_id\": \"%s\", \"moderator_id\": \"%s\", \"data\": {\"user_id\": \"%s\", \"duration\": \"%s\", \"reason\":\"%s\"}}",
                properties.getBroadcasterUserId(), properties.getUserId(), userId, duration, reason);
        
        ResponseEntity<String> response = twitchApiService.sendPostRequest(url, body);
    }
    
}
