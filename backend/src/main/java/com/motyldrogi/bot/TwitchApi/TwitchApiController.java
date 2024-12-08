package com.motyldrogi.bot.TwitchApi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/twitch")
public class TwitchApiController {

    private final TwitchApiService twitchApiService;

    public TwitchApiController(TwitchApiService twitchApiService) {
        this.twitchApiService = twitchApiService;
    }

    @GetMapping("/test")
    public ResponseEntity<String> getTest(){
        System.err.println("Test received !");
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    /**
     * Envoi d'un message sur un canal Twitch.
     * 
     * @param message Le message à envoyer
     * @return La réponse de l'API Twitch
     */
    @PostMapping("/sendMessage")
    public ResponseEntity<String> sendMessage(@RequestParam String message) {
        return twitchApiService.sendMessage(message);
    }

    @PostMapping("/posttest")
    public ResponseEntity<String> sendMessage(){
        return twitchApiService.sendMessage("Test !");
    }

    /**
     * Envoi d'une annonce sur un canal Twitch.
     * 
     * @param message Le message à envoyer
     * @return La réponse de l'API Twitch
     */
    @PostMapping("/sendAnnouncement")
    public ResponseEntity<String> sendAnnouncement(@RequestParam String message) {
        return twitchApiService.sendAnnouncement(message);
    }

    /**
     * Exécution d'une requête POST générique vers l'API Twitch.
     * 
     * @param url L'URL de l'API Twitch
     * @param body Le corps de la requête
     * @return La réponse de l'API Twitch
     */
    @PostMapping("/sendPostRequest")
    public ResponseEntity<String> sendPostRequest(@RequestParam String url, @RequestParam String body) {
        return twitchApiService.sendPostRequest(url, body);
    }

    /**
     * Exécution d'une requête PATCH générique vers l'API Twitch.
     * 
     * @param url L'URL de l'API Twitch
     * @param body Le corps de la requête
     * @return La réponse de l'API Twitch
     */
    @PatchMapping("/sendPatchRequest")
    public ResponseEntity<String> sendPatchRequest(@RequestParam String url, @RequestParam String body) {
        return twitchApiService.sendPatchRequest(url, body);
    }

    /**
     * Exécution d'une requête GET générique vers l'API Twitch.
     * 
     * @param url L'URL de l'API Twitch
     * @return La réponse de l'API Twitch
     */
    @GetMapping("/sendGetRequest")
    public ResponseEntity<String> sendGetRequest(@RequestParam String url) {
        return twitchApiService.sendGetRequest(url);
    }
}
