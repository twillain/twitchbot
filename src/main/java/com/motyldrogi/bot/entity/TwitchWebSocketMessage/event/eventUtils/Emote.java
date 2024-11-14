package com.motyldrogi.bot.entity.TwitchWebSocketMessage.event.eventUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Emote {

    @JsonProperty("emote_set_id")
    private String emoteSetId;

    @JsonProperty("text")
    private String text;

    @JsonProperty("id")
    private String id;
    
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getEmoteSetId() {
        return emoteSetId;
    }
    public void setSetId(String emoteSetId) {
        this.emoteSetId = emoteSetId;
    }

    public String toString(){
        return "Emote: " + id + " emote_set_id: " + emoteSetId + " text: " + text;
    }

    public static void main(String...args){
        String json = "{ \"id\": \"foo\", \"emote_set_id\": \"7\"}";
        ObjectMapper mapper = new ObjectMapper();
        try {  
            System.err.println(json);
            Emote emote = mapper.readValue(json, Emote.class);
            System.out.println(emote.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
    
}
