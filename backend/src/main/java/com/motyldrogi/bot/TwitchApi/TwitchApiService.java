package com.motyldrogi.bot.TwitchApi;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.motyldrogi.bot.configuration.AppProperties;

@Service
public class TwitchApiService {

    private final RestTemplate restTemplate;
    private final AppProperties properties;

    public TwitchApiService(RestTemplate restTemplate, AppProperties appProperties) {
        this.restTemplate = restTemplate;
        this.properties = appProperties;
    }

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + properties.getBearerToken());
        headers.set("Client-Id", properties.getClientId());
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
    /**
     * Send a message to the Twitch channel
     * 
     * @param message The message to send
     * 
     * @return The response of the POST request
     */
    public ResponseEntity<String> sendMessage(String message) {
        String url = "https://api.twitch.tv/helix/chat/messages";
        
        // Corps de la requête avec les variables spécifiques
        String body = String.format("{\"broadcaster_id\": \"%s\", \"sender_id\": \"%s\", \"message\": \"%s\"}",
                properties.getBroadcasterUserId(), properties.getUserId(), message);

        // Créer l'entité HTTP avec le corps et les en-têtes
        HttpEntity<String> entity = new HttpEntity<>(body, createHeaders());
        System.err.println(body);
        // Envoi de la requête POST
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        System.out.println("Response for Message : " + response.getStatusCode());
        return response;
    }

    /**
     * Send an announcement to the Twitch channel
     * @param message The message to send
     * @return The response of the POST request
     */
    public ResponseEntity<String> sendAnnouncement(String message) {
        String url = "https://api.twitch.tv/helix/chat/announcements";
        
        // Corps de la requête avec les variables spécifiques
        String body = String.format("{\"broadcaster_id\": \"%s\", \"moderator_id\": \"%s\", \"message\": \"%s\"}",
                properties.getBroadcasterUserId(), properties.getUserId(), message);

        // Créer l'entité HTTP avec le corps et les en-têtes
        HttpEntity<String> entity = new HttpEntity<>(body, createHeaders());

        // Envoi de la requête POST
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        System.out.println("Response for Announcement : " + response.getStatusCode());
        return response;
    }

    public ResponseEntity<String> sendPostRequest(String url, String body){
        HttpEntity<String> entity = new HttpEntity<>(body, createHeaders());
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        System.out.println("POST Request on: " + url + " Status: " + response.getStatusCode());
        return response;
    }

    public ResponseEntity<String> sendPatchRequest(String url, String body){
        HttpEntity<String> entity = new HttpEntity<>(body, createHeaders());
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PATCH, entity, String.class);
        System.out.println("PATCH Request on: " + url + " Status: " + response.getStatusCode());
        return response;
    }

    public ResponseEntity<String> sendGetRequest(String url) {
        HttpEntity<String> entity = new HttpEntity<>(createHeaders());
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println("GET Request on: " + url + " Status: " + response.getStatusCode());
        return response;
    }

    
    
}
