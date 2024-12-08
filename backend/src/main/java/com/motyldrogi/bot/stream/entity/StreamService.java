package com.motyldrogi.bot.stream.entity;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.motyldrogi.bot.TwitchApi.TwitchApiService;
import com.motyldrogi.bot.configuration.AppProperties;
import com.motyldrogi.bot.entity.TwitchWebSocketMessage.event.StreamEvent;

@Service
public class StreamService {
    
    private final TwitchApiService twitchApiService;
    private final AppProperties properties;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String url = "https://api.twitch.tv/helix/streams";
    private StreamEntity stream;

    public StreamService(TwitchApiService twitchApiService, AppProperties properties){
        this.twitchApiService = twitchApiService;
        this.properties = properties;
        this.stream = null;
    }

    public StreamEntity getStreamInfo(){
        if (!this.stream.getType().equals("live")) return this.stream;
        try {
            String urlWithQueries = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("user_id", properties.getUserId())
                .build()
                .toString();
            ResponseEntity<String> response = twitchApiService.sendGetRequest(urlWithQueries);
            System.out.println(response.getBody());
            JsonNode data = objectMapper.readTree(response.getBody()).path("data").get(0);
            this.stream = objectMapper.convertValue(data, StreamEntity.class);
        } catch (IOException e){
            System.err.println("getStreamInfo : " + e.getMessage());
        }
        return this.stream;
    }

    public int getViewerCount(){
        if (!this.stream.getType().equals("live")) return -1;
        Integer viewerCount = Integer.valueOf("-1");
        try {
            String urlWithQueries = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("user_id", properties.getUserId())
                .build()
                .toString();
            ResponseEntity<String> response = twitchApiService.sendGetRequest(urlWithQueries);

            JsonNode data = objectMapper.readTree(response.getBody()).path("data").get(0).path("viewer_count");
            viewerCount = objectMapper.convertValue(data, Integer.class);
        } catch (IOException e){
            System.err.println("getStreamInfo : " + e.getMessage());
        }
        return viewerCount;
    }

    public StreamEntity setLive(StreamEvent streamEvent){
        if (this.stream == null){
            this.stream = new StreamEntity();
        }
        this.stream.setStreamId(streamEvent.getStreamId());
        this.stream.setUserId(streamEvent.getBroadcasterUserId());
        this.stream.setUserLogin(streamEvent.getBroadcasterUserLogin());
        this.stream.setUserName(streamEvent.getBroadcasterUserName());
        this.stream.setType(streamEvent.getType());
        this.stream.setStartedAt(streamEvent.getStartedAt());

        return this.stream;
    }
    /**
     * Set the stream offline and return the duration of the stream
     * 
     * @return duration of the stream
     */
    public Duration setOffline(){
        Duration duration = this.timeDifference(this.stream.getStartedAt());
        this.stream.setType("Offline");
        this.stream.setStartedAt("");
        return duration;
    }

    public Duration getStreamUptime(){
        return this.timeDifference(this.stream.getStartedAt());
    }

    private Duration timeDifference(String timestamp){

        Instant parseInstant = Instant.parse(timestamp);
        Instant now = Instant.now();

        return Duration.between(parseInstant, now);
    }

    public void subscribeToViewerCount(int seconds){
        
    }

}
