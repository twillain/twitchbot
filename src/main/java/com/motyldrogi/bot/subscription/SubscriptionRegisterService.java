package com.motyldrogi.bot.subscription;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import com.motyldrogi.bot.service.TwitchApiService;

@Service
public class SubscriptionRegisterService {

    private final TwitchApiService twitchApiService;
    private SubscriptionRegistry subscriptionRegistry;
    private Map<String, Subscription> subscriptions = new HashMap<>();
    
    public SubscriptionRegisterService(TwitchApiService twitchApiService, SubscriptionRegistry subscriptionRegistry){
        this.twitchApiService = twitchApiService;
        this.subscriptionRegistry = subscriptionRegistry;
    }

    public void addImplementedSubscriptions(String... types){
        for (String type : types){
            Subscription subscription = subscriptionRegistry.getSubscription(type);
            if (subscription != null){
                subscriptions.put(type, subscription);
                System.out.println("Added subscription : " + type);
            } else {
                System.err.println("No implementation found for type : " + type);
            }
        }
    }

    public void addAllImplementedSubscriptions(){
        subscriptionRegistry.getAllSubscriptions().forEach( (type, subscription) -> {
            subscriptions.put(type, subscription);
        });
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

    public List<String> getAllImplementedSubscriptionTypes(){
        return subscriptionRegistry.getSubscriptionTypes();
    }

    public List<String> getAllRegisteredSubscriptionTypes(){
        return subscriptions.keySet().stream().toList();
    }

    public List<String> getAllImplementedSubscriptionAuthorization(){
        return subscriptionRegistry.getSubscriptionAuthorization();
    }

    public List<String> getAllRegisteredSubscriptionAuthorization(){
        return subscriptions.values().stream().map(Subscription::getAuthorization).toList();
    }

    public void removeAllSubscriptions(){
        subscriptions = new HashMap<>();
    }

    public void registerToSubscriptions(){
        subscriptions.forEach( (type, subscription) -> {
            twitchApiService.subscribeToEvent(type, subscription.getVersion());
            System.out.println("Subscribed to : " + type);
        });
            
    }

    public void registerToAllImplementedSubscriptions(){
        subscriptionRegistry.getAllSubscriptions().forEach( (type, subscription) -> {
            twitchApiService.subscribeToEvent(type, subscription.getVersion());
        });
    }

}
