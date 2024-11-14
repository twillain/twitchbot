package com.motyldrogi.bot.service;

import java.io.IOException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.motyldrogi.bot.configuration.AppProperties;
import com.motyldrogi.bot.entity.TwitchWebSocketMessage.event.eventUtils.Prediction;

@Service
public class TwitchApiService {

    private final RestTemplate restTemplate;
    private final AppProperties properties;
    private final SessionService sessionService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public TwitchApiService(SessionService sessionService,RestTemplate restTemplate, AppProperties appProperties) {
        this.sessionService = sessionService;
        this.restTemplate = restTemplate;
        this.properties = appProperties;
    }

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + properties.getBearerToken());
        headers.set("Client-Id", properties.getClientId());
        headers.set("Content-Type", "application/json");
        return headers;
    }

    // Méthode pour envoyer un message
    public ResponseEntity<String> sendMessage(String message) {
        String url = "https://api.twitch.tv/helix/chat/messages";
        
        // Corps de la requête avec les variables spécifiques
        String body = String.format("{\"broadcaster_id\": \"%s\", \"sender_id\": \"%s\", \"message\": \"%s\"}",
                properties.getBroadcasterUserId(), properties.getUserId(), message);

        // Créer l'entité HTTP avec le corps et les en-têtes
        HttpEntity<String> entity = new HttpEntity<>(body, createHeaders());

        // Envoi de la requête POST
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        System.out.println("Response for Message : " + response.getStatusCode());
        return response;
    }

    // Méthode pour envoyer un message
    public ResponseEntity<String> createPrediction(String predictionTitle, String predictionDuration, String... predictionOutcomes ) {
        String url = "https://api.twitch.tv/helix/predictions";
        
        

        // Corps de la requête avec les variables spécifiques
        String body = "";
        try {
            body = objectMapper.writeValueAsString(new Prediction(properties.getBroadcasterUserId(), predictionTitle, predictionOutcomes, Integer.parseInt(predictionDuration)));
        } catch (IOException e) {
            System.err.println("Error creating prediction : " + e.getMessage());
        }
        System.out.println("Create Prediction Body : " + body);
        // Créer l'entité HTTP avec le corps et les en-têtes
        HttpEntity<String> entity = new HttpEntity<>(body, createHeaders());

        // Envoi de la requête POST
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        System.out.println("Response for Create Prediction : " + response.getStatusCode());
        return response;
    }

    public ResponseEntity<String> sendAnnoucement(String message) {
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

    public ResponseEntity<String> bandUser(String userId, String reason) {
        String url = "https://api.twitch.tv/helix/moderation/bans";
        
        // Corps de la requête avec les variables spécifiques
        String body = String.format("{\"broadcaster_id\": \"%s\", \"moderator_id\": \"%s\", \"data\": {\"user_id\": \"%s\", \"reason\":\"%s\"}}",
                properties.getBroadcasterUserId(), properties.getUserId(), userId, reason);

        // Créer l'entité HTTP avec le corps et les en-têtes
        HttpEntity<String> entity = new HttpEntity<>(body, createHeaders());

        // Envoi de la requête POST
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        System.out.println("Response for Ban : " + response.getStatusCode());
        return response;
    }

    public ResponseEntity<String> timeOutUser(String userId, String duration, String reason) {
        String url = "https://api.twitch.tv/helix/moderation/bans";
        
        // Corps de la requête avec les variables spécifiques
        String body = String.format("{\"broadcaster_id\": \"%s\", \"moderator_id\": \"%s\", \"data\": {\"user_id\": \"%s\", \"duration\": \"%s\", \"reason\":\"%s\"}}",
                properties.getBroadcasterUserId(), properties.getUserId(), userId, duration, reason);

        // Créer l'entité HTTP avec le corps et les en-têtes
        HttpEntity<String> entity = new HttpEntity<>(body, createHeaders());

        // Envoi de la requête POST
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        System.out.println("Response for TimeOut : " + response.getStatusCode());
        return response;
    }
    

    public ResponseEntity<String> subscribeToEvent(String eventSubscriptionCode, String version){
        String url = "https://api.twitch.tv/helix/eventsub/subscriptions";

        String body = String.format(
            "{" +
                "\"type\": \"%s\"," +
                "\"version\": \"%s\"," +
                "\"condition\": {" +
                    "\"user_id\": \"%s\"," +
                    "\"broadcaster_user_id\": \"%s\"," +
                    "\"moderator_user_id\": \"%s\"" +
                "}," +
                "\"transport\": {" +
                    "\"method\": \"websocket\"," +
                    "\"session_id\": \"%s\"" +
                "}" +
            "}",
            eventSubscriptionCode,
            version,
            properties.getUserId(),
            properties.getBroadcasterUserId(),
            properties.getBroadcasterUserId(),
            sessionService.getSessionId()
        );

        HttpEntity<String> entity = new HttpEntity<>(body, createHeaders());

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        System.out.println("Response for Subscription " + eventSubscriptionCode + " : " + response.getStatusCode());
        return response;
    }
}
