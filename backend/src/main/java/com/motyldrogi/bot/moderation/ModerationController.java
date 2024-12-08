package com.motyldrogi.bot.moderation;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequestMapping("/moderation")
@RestController
public class ModerationController {
    
    private final ModerationService moderationService;

    public ModerationController(ModerationService moderationService){
        this.moderationService = moderationService;
    }

    @PostMapping("/ban/{userId}")
    public ResponseEntity<String> banUser(@PathVariable String userId, @RequestBody JsonNode body){
        return moderationService.banUser(userId, body.path("reason").asText());
    }

    @PostMapping("/timeout/{userId}")
    public ResponseEntity<String> timeoutUser(@PathVariable String userId, @RequestBody JsonNode body){
        return moderationService.timeoutUser(userId, body.path("duration").asText(), body.path("reason").asText() );
    }
}
