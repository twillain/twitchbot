package com.motyldrogi.bot.poll;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.motyldrogi.bot.TwitchApi.TwitchApiService;
import com.motyldrogi.bot.configuration.AppProperties;
import com.motyldrogi.bot.entity.TwitchWebSocketMessage.event.eventUtils.Poll;
import com.motyldrogi.bot.poll.entity.PollChoiceEntity;
import com.motyldrogi.bot.poll.entity.PollResponseEntity;
import com.motyldrogi.bot.poll.entity.PollStatusEntity;

@Service
public class PollService {

    private final TwitchApiService twitchApiService;
    private final AppProperties properties;
    private final ObjectMapper objectMapper;

    private static final String URL = "https://api.twitch.tv/helix/polls";

    private PollResponseEntity currentPoll = null;

    public PollService(TwitchApiService twitchApiService, AppProperties properties) {
        this.twitchApiService = twitchApiService;
        this.properties = properties;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE, true);
    }

    public PollResponseEntity getCurrentPoll() {
        return this.currentPoll;
    }

    public PollResponseEntity createPollWithoutChannelPoints(String pollTitle, String pollDuration, String... pollChoices) {
        validatePollParameters(pollTitle, pollChoices, Integer.parseInt(pollDuration), false, 0);
        return createPollWithoutChannelPoints(pollTitle, Integer.parseInt(pollDuration), pollChoices);
    }

    public PollResponseEntity createPollWithoutChannelPoints(String pollTitle, int pollDuration, String... pollChoices) {
        validatePollParameters(pollTitle, pollChoices, pollDuration, false, 0);
        return createPoll(pollTitle, pollDuration, false, 0, pollChoices);
    }

    public PollResponseEntity createPollWithChannelPoints(String pollTitle, String pollDuration, String channelPointsPerVote, String... pollChoices) {
        validatePollParameters(pollTitle, pollChoices, Integer.parseInt(pollDuration), true, Integer.parseInt(channelPointsPerVote));
        return createPollWithChannelPoints(pollTitle, Integer.parseInt(pollDuration), Integer.parseInt(channelPointsPerVote), pollChoices);
    }

    public PollResponseEntity createPollWithChannelPoints(String pollTitle, int pollDuration, int channelPointsPerVote, String... pollChoices) {
        validatePollParameters(pollTitle, pollChoices, pollDuration, true, channelPointsPerVote);
        return createPoll(pollTitle, pollDuration, true, channelPointsPerVote, pollChoices);
    }

    private PollResponseEntity createPoll(String pollTitle, int pollDuration, boolean channelPointsVotingEnabled,
                                          int channelPointsPerVote, String... pollChoices) {
        try {
            String body = objectMapper.writeValueAsString(
                new Poll(properties.getBroadcasterUserId(), pollTitle, pollChoices, channelPointsVotingEnabled, channelPointsPerVote, pollDuration)
            );

            ResponseEntity<String> response = twitchApiService.sendPostRequest(URL, body);

            if (response.hasBody() && response.getBody() != null) {
                JsonNode data = objectMapper.readTree(response.getBody()).path("data").get(0);
                this.currentPoll = objectMapper.convertValue(data, PollResponseEntity.class);
            } else {
                System.err.println("No body in response for : createPoll()");
            }
        } catch (IOException e) {
            throw new RuntimeException("Error parsing poll response: " + e.getMessage(), e);
        }
        return this.currentPoll;
    }

    public List<PollResponseEntity> getAllPolls() {
        try {
            String urlWithQueries = UriComponentsBuilder.fromHttpUrl(URL)
                .queryParam("broadcaster_id", properties.getBroadcasterUserId())
                .build()
                .toString();

            ResponseEntity<String> response = twitchApiService.sendGetRequest(urlWithQueries);

            if (response.hasBody() && response.getBody() != null) {
                JsonNode pollsResponse = objectMapper.readTree(response.getBody()).path("data");
                return objectMapper.convertValue(pollsResponse, new TypeReference<List<PollResponseEntity>>() {});
            } else {
                System.err.println("No body in response for : getAllPolls()");
                return Collections.emptyList();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error parsing all polls: " + e.getMessage(), e);
        }
    }

    public PollChoiceEntity endCurrentPoll(PollStatusEntity pollStatus) {
        if (currentPoll == null) {
            throw new IllegalStateException("No current poll to end");
        }

        try {
            String body = String.format("""
                {
                    "broadcaster_id": "%s",
                    "id": "%s",
                    "status": "%s"
                }
                """,
                properties.getBroadcasterUserId(), currentPoll.getId(), pollStatus.getStatus());

            ResponseEntity<String> response = twitchApiService.sendPatchRequest(URL, body);

            if (response.hasBody() && response.getBody() != null) {
                JsonNode data = objectMapper.readTree(response.getBody()).path("data").get(0);
                PollResponseEntity responseEntity = objectMapper.convertValue(data, PollResponseEntity.class);
                this.currentPoll = null;
                return responseEntity.getWinningChoice();
            } else {
                System.err.println("No body in response for : endCurrentPoll()");
                return null;
            }
        } catch (IOException e) {
            throw new RuntimeException("Error parsing end poll response: " + e.getMessage(), e);
        }
    }

    private void validatePollParameters(String pollTitle, String[] pollChoices, int pollDuration, boolean channelPointsVotingEnabled, int channelPointsPerVote) {
        if (!StringUtils.hasText(pollTitle)) {
            throw new IllegalArgumentException("Poll title cannot be null or empty");
        }
        if (pollChoices == null || pollChoices.length < 2) {
            throw new IllegalArgumentException("There must be at least two poll choices");
        }
        if (pollDuration < 15 || pollDuration > 1800) {
            throw new IllegalArgumentException("Poll duration must be between 15 and 1800 seconds");
        }
        if (channelPointsVotingEnabled && (channelPointsPerVote < 1 || channelPointsPerVote > 1_000_000)) {
            throw new IllegalArgumentException("Channel points per vote must be between 1 and 1,000,000");
        }
    }
}
