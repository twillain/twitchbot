package com.motyldrogi.bot.chatNotification;

import org.springframework.stereotype.Component;

import com.motyldrogi.bot.FrontWebSocket.BroadcastService;
import com.motyldrogi.bot.TwitchApi.TwitchApiService;
import com.motyldrogi.bot.entity.TwitchWebSocketMessage.event.eventUtils.Payload;
import com.motyldrogi.bot.notification.EventNotificationHandler;

@Component
public class ChatNotificationHandler implements EventNotificationHandler<ChatNotificationEvent> {

    private final TwitchApiService twitchApiService;
    private final BroadcastService broadcastService;

    public ChatNotificationHandler(
        TwitchApiService twitchApiService,
        BroadcastService broadcastService
    ){
        this.twitchApiService = twitchApiService;
        this.broadcastService = broadcastService;
    }

    @Override
    public void handleNotification(Payload payload){
        ChatNotificationEvent event = (ChatNotificationEvent) payload.getEvent();
        String eventType = event.getNoticeType();
        System.out.println("Event type : " + eventType);
        switch (eventType) {
            case "sub":
                handleSubChatNotification(event);
                break;
            case "resub":
                handleResubChatNotification(event);
                break;
            case "sub_gift":
                handleSubGiftChatNotification(event);
                break;
            case "community_sub_gift":
                handleCommunitySubGiftChatNotification(event);
                break;
            case "gift_paid_upgrade":
                handleGiftPaidUpgradeChatNotification(event);
                break;
            case "prime_paid_upgrade":
                handlePrimePaidUpgradeChatNotification(event);
                break;
            case "raid":
                handleRaidChatNotification(event);
                break;
            case "unraid":
                handleUnraidChatNotification(event);
                break;
            case "pay_it_forward":
                handlePayItForwardChatNotification(event);
                break;
            case "announcement":
                handleAnnouncementChatNotification(event);
                break;
            case "bits_badge_tier":
                handleBitsBadgeTierChatNotification(event);
                break;
            case "charity_donation":
                handleCharityDonationChatNotification(event);
                break;
            case "shared_chat_sub":
                handleSharedChatSubNotification(event);
                break;
            case "shared_chat_resub":
                handleSharedChatResubNotification(event);
                break;
            case "shared_chat_sub_gift":
                handleSharedChatSubGiftNotification(event);
                break;
            case "shared_chat_community_sub_gift":
                handleSharedChatCommunitySubGiftNotification(event);
                break;
            case "shared_chat_gift_paid_upgrade":
                handleSharedChatGiftPaidUpgradeNotification(event);
                break;
            case "shared_chat_prime_paid_upgrade":
                handleSharedChatPrimePaidUpgradeNotification(event);
                break;
            case "shared_chat_raid":
                handleSharedChatRaidNotification(event);
                break;
            case "shared_chat_pay_it_forward":
                handleSharedChatPayItForwardNotification(event);
                break;
            case "shared_chat_announcement":
                handleSharedChatAnnouncementNotification(event);
                break;
            default:
                handleUnknownChatNotification(eventType, event);
                break;
        }


    }

    public void handleSubChatNotification(ChatNotificationEvent event) {
        // TODO: Implement handling for sub event
    }
    
    public void handleResubChatNotification(ChatNotificationEvent event) {
        // TODO: Implement handling for resub event
    }
    
    public void handleSubGiftChatNotification(ChatNotificationEvent event) {
        // TODO: Implement handling for sub_gift event
    }
    
    public void handleCommunitySubGiftChatNotification(ChatNotificationEvent event) {
        // TODO: Implement handling for community_sub_gift event
    }
    
    public void handleGiftPaidUpgradeChatNotification(ChatNotificationEvent event) {
        // TODO: Implement handling for gift_paid_upgrade event
    }
    
    public void handlePrimePaidUpgradeChatNotification(ChatNotificationEvent event) {
        // TODO: Implement handling for prime_paid_upgrade event
    }
    
    public void handleRaidChatNotification(ChatNotificationEvent event) {
        // TODO: Implement handling for raid event
    }
    
    public void handleUnraidChatNotification(ChatNotificationEvent event) {
        // TODO: Implement handling for unraid event
    }
    
    public void handlePayItForwardChatNotification(ChatNotificationEvent event) {
        // TODO: Implement handling for pay_it_forward event
    }
    
    public void handleAnnouncementChatNotification(ChatNotificationEvent event) {
        System.out.println("Announcement : " + event.getMessage().getText());
        broadcastService.broadcastNotification("/topic/chat/announcement", event);
    }
    
    public void handleBitsBadgeTierChatNotification(ChatNotificationEvent event) {
        // TODO: Implement handling for bits_badge_tier event
    }
    
    public void handleCharityDonationChatNotification(ChatNotificationEvent event) {
        // TODO: Implement handling for charity_donation event
    }
    
    public void handleSharedChatSubNotification(ChatNotificationEvent event) {
        // TODO: Implement handling for shared_chat_sub event
    }
    
    public void handleSharedChatResubNotification(ChatNotificationEvent event) {
        // TODO: Implement handling for shared_chat_resub event
    }
    
    public void handleSharedChatSubGiftNotification(ChatNotificationEvent event) {
        // TODO: Implement handling for shared_chat_sub_gift event
    }
    
    public void handleSharedChatCommunitySubGiftNotification(ChatNotificationEvent event) {
        // TODO: Implement handling for shared_chat_community_sub_gift event
    }
    
    public void handleSharedChatGiftPaidUpgradeNotification(ChatNotificationEvent event) {
        // TODO: Implement handling for shared_chat_gift_paid_upgrade event
    }
    
    public void handleSharedChatPrimePaidUpgradeNotification(ChatNotificationEvent event) {
        // TODO: Implement handling for shared_chat_prime_paid_upgrade event
    }
    
    public void handleSharedChatRaidNotification(ChatNotificationEvent event) {
        // TODO: Implement handling for shared_chat_raid event
    }
    
    public void handleSharedChatPayItForwardNotification(ChatNotificationEvent event) {
        // TODO: Implement handling for shared_chat_pay_it_forward event
    }
    
    public void handleSharedChatAnnouncementNotification(ChatNotificationEvent event) {
        // TODO: Implement handling for shared_chat_announcement event
    }
    
    public void handleUnknownChatNotification(String eventType, ChatNotificationEvent event) {
        System.err.println("Chat Notification : - eventType: " + eventType);
    }
    
    
}
