package com.motyldrogi.bot.entity.TwitchWebSocketMessage.event.eventUtils;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Fragment {

    @JsonProperty("type")
    private String type;

    @JsonProperty("text")
    private String text;

    @JsonDeserialize(contentAs = Cheermote.class)
    @JsonFormat(with = {JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, JsonFormat.Feature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED})
    @JsonProperty("cheermote")
    private List<Cheermote> cheermotes;

    @JsonDeserialize(contentAs = Emote.class)
    @JsonFormat(with = {JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, JsonFormat.Feature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED})
    @JsonProperty("emote")
    private List<Emote> emotes;

    @JsonProperty("mention")
    private Mention mention;
    
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public List<Cheermote> getCheermotes() {
        return cheermotes;
    }
    public void setCheermotes(List<Cheermote> cheermotes) {
        this.cheermotes = cheermotes;
    }
    public List<Emote> getEmotes() {
        return emotes;
    }
    public void setEmotes(List<Emote> emotes) {
        this.emotes = emotes;
    }
    public Mention getMention() {
        return mention;
    }
    public void setMention(Mention mention) {
        this.mention = mention;
    }

    public String toString(){
        return "Fragement : " + this.type + " text: " + this.text + " cheermotes: " + this.cheermotes + " emotes: " + this.emotes + " mention: " + this.mention;
    }

    public static void main(String...args){
        String json = "{ \"type\": \"emote\", \"text\": \"hey world\", \"cheermote\": null, \"emote\": { \"emote_set_id\": \"8\", \"text\": \"foo\", \"id\": \"rab\" }, \"mention\": null }";
        ObjectMapper mapper = new ObjectMapper();
        try {  
            System.err.println(json);
            Fragment fragment = mapper.readValue(json, Fragment.class);
            System.out.println(fragment.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    
}
