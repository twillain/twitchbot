package com.motyldrogi.bot.notification;

import com.motyldrogi.bot.entity.TwitchWebSocketMessage.event.SubscribeEvent;
import com.motyldrogi.bot.entity.TwitchWebSocketMessage.event.SubscribeGiftEvent;
import com.motyldrogi.bot.entity.TwitchWebSocketMessage.event.eventUtils.Payload;
import com.motyldrogi.bot.service.TwitchApiService;
import com.motyldrogi.bot.subs.SubService;
import com.motyldrogi.bot.user.UserEntity;
import com.motyldrogi.bot.user.UserService;

public class SubscribeNotificationHandler implements EventNotificationHandler<SubscribeEvent> {

    private final TwitchApiService twitchApiService;
    private final UserService userService;
    private final SubService subService;

    public SubscribeNotificationHandler(TwitchApiService twitchApiService, UserService userService, SubService subService) {
        this.twitchApiService = twitchApiService;
        this.userService = userService;
        this.subService = subService;
    }

    public void handleNotification(Payload payload){

        switch (payload.getSubscription().getType()){
            case "channel.subscribe":
                handleChannelSubscription(payload);
                break;
            case "channel.subscription.end":
                handleChannelSubscriptionEnd(payload);
                break;
            case "channel.subscription.gift":
                handleChannelSubscriptionGift(payload);
                break;
            default:
                break;
        }

    }

    public void handleChannelSubscription(Payload payload){

        SubscribeEvent event = (SubscribeEvent) payload.getEvent();

        UserEntity user = userService.getUserByEventOrCreateEntity(event);
        if (user == null) throw new IllegalArgumentException("No user found with ID : " + event.getUserId());

        subService.addSub(user, payload.getSubscription().getId(), payload.getSubscription().getCreatedAt(), event.getTier(), event.isGift());

        String messageToSend;
        if (event.isGift()) {
            messageToSend = String.format("Thank you for the subscription tier %s @%s! Don't forget to thank your subgifter", event.getTier() ,event.getUserName());
        } else {
            messageToSend = String.format("Thank you for the subscription tier %s @%s !", event.getTier(), event.getUserName());
        }
        twitchApiService.sendMessage(messageToSend);
    }

    public void handleChannelSubscriptionEnd(Payload payload){
        SubscribeEvent event = (SubscribeEvent) payload.getEvent();

        UserEntity user = userService.getUserByEventOrCreateEntity(event);
        if (user == null) throw new IllegalArgumentException("No user found with ID : " + event.getUserId());

        subService.endSub(user, payload.getSubscription().getCreatedAt());
        
        twitchApiService.sendMessage("Hope you'll be back soon in the sub family "+ user.getName() + "!");
    }

    public void handleChannelSubscriptionGift(Payload payload){
        SubscribeGiftEvent event = (SubscribeGiftEvent) payload.getEvent();

        UserEntity user = userService.getUserByEventOrCreateEntity(event);
        if (user == null) throw new IllegalArgumentException("No user found with ID : " + event.getUserId());

        if (event.isAnonymous()){
            subService.addAnonymousSubGift(user, payload.getSubscription().getId(), payload.getSubscription().getCreatedAt(), event.getTotal());
        } else {
            subService.addNotAnonymousSubGift(user, payload.getSubscription().getId(), payload.getSubscription().getCreatedAt(), event.getTotal(), event.getTier(), event.getCumulativeTotal());
        }

        String messageToSend = String.format("Thank you @%s for the %s gifted subscriptions tier %s !", event.getUserName(), event.getTotal(), event.getTier());
        twitchApiService.sendMessage(messageToSend);
    }
    
}
