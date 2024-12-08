package com.motyldrogi.bot.subscription;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.motyldrogi.bot.chatNotification.ChatNotificationEvent;
import com.motyldrogi.bot.chatNotification.ChatNotificationHandler;
import com.motyldrogi.bot.entity.TwitchWebSocketMessage.event.FollowEvent;
import com.motyldrogi.bot.entity.TwitchWebSocketMessage.event.MessageEvent;
import com.motyldrogi.bot.entity.TwitchWebSocketMessage.event.StreamEvent;
import com.motyldrogi.bot.entity.TwitchWebSocketMessage.event.SubscribeEvent;
import com.motyldrogi.bot.entity.TwitchWebSocketMessage.event.SubscribeGiftEvent;
import com.motyldrogi.bot.notification.*;


@Component
public class SubscriptionRegistry {
    
    private Map<String, Subscription> subscriptions = new HashMap<>();

    public SubscriptionRegistry(
        FollowNotificationHandler followNotificationHandler,
        MessageNotificationHandler messageNotificationHandler,
        SubscribeNotificationHandler subscribeNotificationHandler,
        StreamNotificationHandler streamNotificationHandler,
        ChatNotificationHandler chatNotificationHandler
    ) {
        ArrayList<Subscription> subs = new ArrayList<>();
        subs.add(new Subscription(
            "channel.follow",
            "2",
            followNotificationHandler,
            FollowEvent.class,
            "moderator:read:followers"));
        subs.add(new Subscription(
            "channel.chat.message",
            "1",
            messageNotificationHandler,
            MessageEvent.class,
            "user:read:chat"));
        subs.add(new Subscription(
            "channel.subscribe",
            "1",
            subscribeNotificationHandler,
            SubscribeEvent.class,
            "channel:read:subscriptions"));
        subs.add(new Subscription(
            "channel.subscription.end",
            "1",
            subscribeNotificationHandler,
            SubscribeEvent.class,
            "channel:read:subscriptions"
        ));
        subs.add(new Subscription(
            "channel.subscription.gift",
            "1",
            subscribeNotificationHandler,
            SubscribeGiftEvent.class,
            "channel:read:subscriptions"
        ));
        subs.add(new Subscription(
            "stream.online",
            "1",
            streamNotificationHandler,
            StreamEvent.class,
            ""
            ));
        subs.add(new Subscription(
            "stream.offline",
            "1",
            streamNotificationHandler,
            StreamEvent.class,
            ""
            ));
        subs.add(new Subscription(
            "channel.chat.notification",
            "1",
            chatNotificationHandler,
            ChatNotificationEvent.class,
            "user:read:chat"
        ));

        for (Subscription subscription : subs){
            subscriptions.put(subscription.getType(), subscription);
        }
    }

    public void addSubscription(Subscription subscription){
        subscriptions.put(subscription.getType(), subscription);
    }

    public void removeSubscription(String type){
        subscriptions.remove(type);
    }

    public Subscription getSubscription(String type){
        return subscriptions.get(type);
    }

    public Map<String, Subscription> getAllSubscriptions(){
        return subscriptions;
    }

    public List<String> getSubscriptionTypes(){
        return new ArrayList<>(subscriptions.keySet());
    }

    public List<String> getSubscriptionAuthorization(){
        List<String> authorizations = new ArrayList<>();
        for (Subscription subscription : subscriptions.values()){
            authorizations.add(subscription.getAuthorization());
        }
        return authorizations;
    }

    public void removeAllSubscriptions(){
        subscriptions = new HashMap<>();
    }
}
