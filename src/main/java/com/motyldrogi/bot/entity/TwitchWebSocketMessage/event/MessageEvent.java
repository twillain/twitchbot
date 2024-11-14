package com.motyldrogi.bot.entity.TwitchWebSocketMessage.event;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.motyldrogi.bot.entity.TwitchWebSocketMessage.event.eventUtils.Badge;
import com.motyldrogi.bot.entity.TwitchWebSocketMessage.event.eventUtils.Message;

public class MessageEvent extends Event {

    @JsonProperty("message_id")
    private String messageId;

    @JsonProperty("color")
    private String color;

    @JsonProperty("message_type")
    private String messageType;

    @JsonProperty("cheer")
    private String cheer;

    @JsonProperty("reply")
    private String reply;

    @JsonProperty("channel_points_custom_reward_id")
    private String channelPointsCustomRewardId;

    @JsonProperty("channel_points_animation_id")
    private String channelPointsAnimationId;

    @JsonProperty("source_broadcaster_user_id")
    private String sourceBroadcasterUserId;

    @JsonProperty("source_broadcaster_user_login")
    private String sourceBroadcasterUserLogin;

    @JsonProperty("source_broadcaster_user_name")
    private String sourceBroadcasterUserName;

    @JsonProperty("source_message_id")
    private String sourceMessageId;

    @JsonProperty("chatter_user_id")
    private String chatterUserId;

    @JsonProperty("chatter_user_login")
    private String chatterUserLogin;

    @JsonProperty("chatter_user_name")
    private String chatterUserName;

    @JsonFormat(with = {JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, JsonFormat.Feature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED})
    @JsonDeserialize(contentAs = Badge.class)
    @JsonProperty("source_badges")
    private List<Badge> sourceBadges;

    @JsonFormat(with = {JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, JsonFormat.Feature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED})
    @JsonDeserialize(contentAs = Badge.class)
    @JsonProperty("badges")
    private List<Badge> badges;

    @JsonDeserialize(contentAs = Message.class)
    @JsonProperty("message")
    private Message message;

    public String getChatterUserId() {
        return chatterUserId;
    }

    public void setChatterUserId(String chatterUserId) {
        this.chatterUserId = chatterUserId;
    }

    public String getChannelPointsAnimationId() {
        return channelPointsAnimationId;
    }

    public void setChannelPointsAnimationId(String channelPointsAnimationId) {
        this.channelPointsAnimationId = channelPointsAnimationId;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getCheer() {
        return cheer;
    }

    public void setCheer(String cheer) {
        this.cheer = cheer;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getChannelPointsCustomRewardId() {
        return channelPointsCustomRewardId;
    }

    public void setChannelPointsCustomRewardId(String channelPointsCustomRewardId) {
        this.channelPointsCustomRewardId = channelPointsCustomRewardId;
    }

    public String getSourceBroadcasteruserId() {
        return sourceBroadcasterUserId;
    }

    public void setSourceBroadcasteruserId(String sourceBroadcasterUserId) {
        this.sourceBroadcasterUserId = sourceBroadcasterUserId;
    }

    public String getSourceBroadcasterUserLogin() {
        return sourceBroadcasterUserLogin;
    }

    public void setSourceBroadcasterUserLogin(String sourceBroadcasterUserLogin) {
        this.sourceBroadcasterUserLogin = sourceBroadcasterUserLogin;
    }

    public String getSourceBroadcasterUserName() {
        return sourceBroadcasterUserName;
    }

    public void setSourceBroadcasterUserName(String sourceBroadcasterUserName) {
        this.sourceBroadcasterUserName = sourceBroadcasterUserName;
    }

    public String getSourceMessageId() {
        return sourceMessageId;
    }

    public void setSourceMessageId(String sourceMessageId) {
        this.sourceMessageId = sourceMessageId;
    }

    public List<Badge> getSourceBadges() {
        return sourceBadges;
    }

    public void setSourceBadges(List<Badge> sourceBadges) {
        this.sourceBadges = sourceBadges;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String getSourceBroadcasterUserId() {
        return sourceBroadcasterUserId;
    }

    public void setSourceBroadcasterUserId(String sourceBroadcasterUserId) {
        this.sourceBroadcasterUserId = sourceBroadcasterUserId;
    }

    public List<Badge> getBadges() {
        return badges;
    }

    public void setBadges(List<Badge> badges) {
        this.badges = badges;
    }

    public String getChatterUserLogin() {
        return chatterUserLogin;
    }

    public void setChatterUserLogin(String chatterUserLogin) {
        this.chatterUserLogin = chatterUserLogin;
    }

    public String getChatterUserName() {
        return chatterUserName;
    }

    public void setChatterUserName(String chatterUserName) {
        this.chatterUserName = chatterUserName;
    }

    public String toString(){
        return "MessageEvent{" +
                "messageId='" + messageId + '\'' +
                ", color='" + color + '\'' +
                ", messageType='" + messageType + '\'' +
                ", cheer='" + cheer + '\'' +
                ", reply='" + reply + '\'' +
                ", channelPointsCustomRewardId='" + channelPointsCustomRewardId + '\'' +
                ", channelPointsAnimationId='" + channelPointsAnimationId + '\'' +
                ", sourceBroadcasterUserId='" + sourceBroadcasterUserId + '\'' +
                ", sourceBroadcasterUserLogin='" + sourceBroadcasterUserLogin + '\'' +
                ", sourceBroadcasterUserName='" + sourceBroadcasterUserName + '\'' +
                ", sourceMessageId='" + sourceMessageId + '\'' +
                ", chatterUserId='" + chatterUserId + '\'' +
                ", chatterUserLogin='" + chatterUserLogin + '\'' +
                ", chatterUserName='" + chatterUserName + '\'' +
                ", sourceBadges=" + sourceBadges +
                ", badges=" + badges +
                ", message=" + message +
                '}';
    }

    public static void main(String...args){
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "{\"reply\" : \"oui\", \"message\" : null}";

        try {
            MessageEvent messageEvent = objectMapper.readValue(json, MessageEvent.class);
            System.out.println("Broadcaster User Id : " + messageEvent.getBroadcasterUserId().equals("920166633"));
            System.out.println("Broadcaster User Login : " + messageEvent.getBroadcasterUserLogin().equals("qiriku3d"));
            System.out.println("Broadcaster User Name : " + messageEvent.getBroadcasterUserName().equals("qiriku3d"));
            System.out.println("User Id : " + messageEvent.getUserId().equals("920166633"));
            System.out.println("User Login : " + messageEvent.getUserLogin().equals("qiriku3d"));
            System.out.println("User Name : " + messageEvent.getUserName().equals("qiriku3d"));
            System.out.println("Message Id : " + messageEvent.getMessageId().equals("06553d00-3ae9-49ce-8ea9-acfe712ffff7"));
            System.out.println("Message Text : " + messageEvent.getMessage().getText().equals("s"));
            System.out.println("Fragment Type : " + messageEvent.getMessage().getFragments().get(0).getType().equals("text"));
            System.out.println("Fragment Text : " + messageEvent.getMessage().getFragments().get(0).getText().equals("s"));
            System.out.println("Badge Id : " + messageEvent.getBadges().get(0).getId().equals("broadcaster"));
            System.out.println("Badge Set Id : " + messageEvent.getBadges().get(0).getSetId().equals("1"));
            System.out.println("Badge Info : " + messageEvent.getBadges().get(0).getInfo().equals(""));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
