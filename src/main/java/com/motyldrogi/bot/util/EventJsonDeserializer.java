package com.motyldrogi.bot.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.motyldrogi.bot.entity.TwitchWebSocketMessage.event.*;


public class EventJsonDeserializer extends JsonDeserializer<Event> {
    
    public Event deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectMapper objectMapper = (ObjectMapper) p.getCodec();
        ObjectNode node = objectMapper.readTree(p);
        if (node.has("followed_at")) {
            return objectMapper.readValue(node.toString(), FollowEvent.class);
        } else if (node.has("is_gift")) {
            return objectMapper.readValue(node.toString(), SubscribeEvent.class);
        } else if (node.has("message")) {
            System.err.println("Message event");
            return objectMapper.readValue(node.toString(), MessageEvent.class);
        } else if (node.has("reconnecting")) {
            return objectMapper.readValue(node.toString(), ReconnectEvent.class);
        }else {
            System.err.println("Infinite loop");
            return objectMapper.readValue(node.toString(), Event.class);
        }
    }

}
