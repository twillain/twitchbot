package com.motyldrogi.bot.predictions;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.motyldrogi.bot.configuration.AppProperties;
import com.motyldrogi.bot.entity.TwitchWebSocketMessage.event.eventUtils.Prediction;
import com.motyldrogi.bot.predictions.entity.PredictionResponseEntity;
import com.motyldrogi.bot.service.TwitchApiService;

@Service
public class PredictionService {
    
    private final TwitchApiService twitchApiService;
    private final AppProperties properties;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String url = "https://api.twitch.tv/helix/predictions";

    private PredictionResponseEntity currentPrediction = null;

    public PredictionService(
        TwitchApiService twitchApiService,
        AppProperties properties
    ){
        this.twitchApiService = twitchApiService;
        this.properties = properties;
        objectMapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE, true);
    }

    public PredictionResponseEntity getCurrentPrediction() {
        return this.currentPrediction;
    }

    public PredictionResponseEntity createPrediction(String predictionTitle,String predictionDuration, String... predictionOutcomes){
        return createPrediction(predictionTitle, Integer.parseInt(predictionDuration), predictionOutcomes);
    }
    
    /**
     * Create a prediction on the Twitch channel
     * 
     * @pre The user must be a moderator of the channel
     * @pre The channel must have channel points activated
     * 
     * @param predictionTitle The title of the prediction
     * @param predictionDuration The duration of the prediction in seconds
     * @param predictionOutcomes The different outcomes of the prediction
     * 
     * @return The response of the POST request
     */
    public PredictionResponseEntity createPrediction(String predictionTitle, int predictionDuration, String... predictionOutcomes){
        try {
            String body = objectMapper.writeValueAsString(new Prediction(properties.getBroadcasterUserId(), predictionTitle, predictionOutcomes, predictionDuration));
            ResponseEntity<String> response = twitchApiService.sendPostRequest(url, body);

            if (response.hasBody()) {
                JsonNode data = objectMapper.readTree(response.getBody()).get("data").get(0);
                this.currentPrediction = objectMapper.convertValue(data, PredictionResponseEntity.class);
            } else {
                System.err.println("No body in prediction response");
            }
        } catch (IOException e) {
            System.err.println("Error parsing prediction response : " + e.getMessage());
        }
        return this.currentPrediction;
    }

    public PredictionResponseEntity resolveCurrentPrediction(String winningOutcomeTitle){
        try {
            if (currentPrediction == null){
                System.err.println("No current prediction to end");
                return null;
            }
            return updateCurrentPrediction(currentPrediction.getId(), "RESOLVED", currentPrediction.getOutcomeFromTitle(winningOutcomeTitle).getId());
        } catch (IllegalArgumentException e){
            twitchApiService.sendMessage(e.getMessage());
            return null;
        }
        
    }

    public PredictionResponseEntity cancelCurrentPrediction(){
        if (currentPrediction == null){
            System.err.println("No current prediction to cancel");
            return null;
        }
        return updateCurrentPrediction(currentPrediction.getId(), "CANCELED", "");
    }

    public PredictionResponseEntity lockCurrentPrediction(){
        if (currentPrediction == null){
            System.err.println("No current prediction to lock");
            return null;
        }
        return updateCurrentPrediction(currentPrediction.getId(), "LOCKED", "");
    }

    private PredictionResponseEntity updateCurrentPrediction(String predictionId, String status, String winningOutcomeId){
        if (currentPrediction == null){
            System.err.println("No current poll");
            return null;
        }
        try {
            String body = String.format("""
                {
                    "broadcaster_id": "%s",
                    "id": "%s",
                    "status": "%s",
                    "winning_outcome_id": "%s"
                }
                """,
                properties.getBroadcasterUserId(), predictionId, status, winningOutcomeId);
            
            ResponseEntity<String> response = twitchApiService.sendPatchRequest(this.url, body);
            System.err.println(response.getBody());
            if (response.hasBody() && response.getBody() != null){
                JsonNode data = objectMapper.readTree(response.getBody()).path("data").get(0);
                PredictionResponseEntity responseEntity = objectMapper.convertValue(data, PredictionResponseEntity.class);
                //System.out.println(responseEntity.getTitle());
                this.currentPrediction = null;
                return responseEntity;
            } else {
                System.err.println("No body found in reponse for : updateCurrentPrediction()");
                return null;
            }

        } catch (IOException e){
            System.err.println(e.getMessage());
            return null;
        }
    }
        
}
