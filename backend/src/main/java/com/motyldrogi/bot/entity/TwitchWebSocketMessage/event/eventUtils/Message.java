package com.motyldrogi.bot.entity.TwitchWebSocketMessage.event.eventUtils;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class Message {

    @JsonProperty("text")
    private String text;

    @JsonFormat(with = {JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, JsonFormat.Feature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED})
    @JsonDeserialize(contentAs = Fragment.class)
    @JsonProperty("fragments")
    private List<Fragment> fragments;
    
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public List<Fragment> getFragments() {
        return fragments;
    }
    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
    }

    public String toString(){
        return "Message: " + text + " fragments: " + fragments;
    }

    public static void main(String...args){
        String json = "{\"text\":\"s\",\"fragments\":[{\"type\":\"text\",\"text\":\"s\",\"cheermote\":null,\"emote\":null,\"mention\":null}]}";
        ObjectMapper mapper = new ObjectMapper();
        try {
            System.err.println(json);
            Message message = mapper.readValue(json, Message.class);
            System.out.println(message.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(json);
    }
}
