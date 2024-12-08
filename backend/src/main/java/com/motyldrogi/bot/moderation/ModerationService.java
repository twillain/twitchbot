package com.motyldrogi.bot.moderation;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.motyldrogi.bot.TwitchApi.TwitchApiService;
import com.motyldrogi.bot.configuration.AppProperties;

@Service
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
    public ResponseEntity<String> banUser(String userId, String reason){
        UriComponents uri = UriComponentsBuilder.newInstance()
            .fromHttpUrl(url)
            .queryParam("broadcaster_id", properties.getBroadcasterUserId())
            .queryParam("moderator_id", properties.getUserId())
            .build();
        System.out.println("URI : " + uri.toString());
        String body = String.format("{\"data\": {\"user_id\": \"%s\", \"reason\":\"%s\"}}",
                userId, reason);
        
        return twitchApiService.sendPostRequest(uri.toString(), body);
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
    public ResponseEntity<String> timeoutUser(String userId, String duration, String reason){
        UriComponents uri = UriComponentsBuilder.newInstance()
            .fromHttpUrl(url)
            .queryParam("broadcaster_id", properties.getBroadcasterUserId())
            .queryParam("moderator_id", properties.getUserId())
            .build();
        System.out.println("URI : " + uri.toString());
        
        String body = String.format("{\"data\": {\"user_id\": \"%s\", \"duration\": \"%s\", \"reason\":\"%s\"}}",
                userId, duration, reason);
        
        return twitchApiService.sendPostRequest(uri.toString(), body);
    }

    
    
}
