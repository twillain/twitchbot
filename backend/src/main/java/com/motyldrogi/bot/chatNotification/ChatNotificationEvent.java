package com.motyldrogi.bot.chatNotification;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.motyldrogi.bot.chatNotification.entity.*;
import com.motyldrogi.bot.entity.TwitchWebSocketMessage.event.Event;


public class ChatNotificationEvent extends Event {
    
    
    @JsonProperty("broadcaster_user_id")
    private String broadcasterUserId;

    @JsonProperty("broadcaster_user_name")
    private String broadcasterUserName;

    @JsonProperty("broadcaster_user_login")
    private String broadcasterUserLogin;

    @JsonProperty("chatter_user_id")
    private String chatterUserId;

    @JsonProperty("chatter_user_name")
    private String chatterUserName;

    @JsonProperty("chatter_is_anonymous")
    private boolean chatterIsAnonymous;

    @JsonProperty("color")
    private String color;

    @JsonProperty("badges")
    private List<Badge> badges;

    @JsonProperty("system_message")
    private String systemMessage;

    @JsonProperty("message_id")
    private String messageId;

    @JsonProperty("message")
    private Message message;

    @JsonProperty("notice_type")
    private String noticeType;

    @JsonProperty("sub")
    private Sub sub;

    @JsonProperty("resub")
    private Resub resub;

    @JsonProperty("sub_gift")
    private SubGift subGift;

    @JsonProperty("community_sub_gift")
    private CommunitySubGift communitySubGift;

    @JsonProperty("gift_paid_upgrade")
    private GiftPaidUpgrade giftPaidUpgrade;

    @JsonProperty("prime_paid_upgrade")
    private PrimePaidUpgrade primePaidUpgrade;

    @JsonProperty("pay_it_forward")
    private PayItForward payItForward;

    @JsonProperty("raid")
    private Raid raid;

    @JsonProperty("unraid")
    private Unraid unraid;

    @JsonProperty("announcement")
    private Announcement announcement;

    @JsonProperty("bits_badge_tier")
    private BitsBadgeTier bitsBadgeTier;

    @JsonProperty("charity_donation")
    private CharityDonation charityDonation;

    @JsonProperty("source_broadcaster_user_id")
    private String sourceBroadcasterUserId;

    @JsonProperty("source_broadcaster_user_name")
    private String sourceBroadcasterUserName;

    @JsonProperty("source_broadcaster_user_login")
    private String sourceBroadcasterUserLogin;

    @JsonProperty("source_message_id")
    private String sourceMessageId;

    @JsonProperty("source_badges")
    private List<Badge> sourceBadges;

    @JsonProperty("shared_chat_sub")
    private SharedChatSub sharedChatSub;

    @JsonProperty("shared_chat_resub")
    private SharedChatResub sharedChatResub;

    @JsonProperty("shared_chat_sub_gift")
    private SharedChatSubGift sharedChatSubGift;

    @JsonProperty("shared_chat_community_sub_gift")
    private SharedChatCommunitySubGift sharedChatCommunitySubGift;

    @JsonProperty("shared_chat_gift_paid_upgrade")
    private SharedChatGiftPaidUpgrade sharedChatGiftPaidUpgrade;

    @JsonProperty("shared_chat_prime_paid_upgrade")
    private SharedChatPrimePaidUpgrade sharedChatPrimePaidUpgrade;

    @JsonProperty("shared_chat_pay_it_forward")
    private SharedChatPayItForward sharedChatPayItForward;

    @JsonProperty("shared_chat_raid")
    private SharedChatRaid sharedChatRaid;

    @JsonProperty("shared_chat_announcement")
    private SharedChatAnnouncement sharedChatAnnouncement;

    public String getBroadcasterUserId() {
        return broadcasterUserId;
    }

    public void setBroadcasterUserId(String broadcasterUserId) {
        this.broadcasterUserId = broadcasterUserId;
    }

    public String getBroadcasterUserName() {
        return broadcasterUserName;
    }

    public void setBroadcasterUserName(String broadcasterUserName) {
        this.broadcasterUserName = broadcasterUserName;
    }

    public String getBroadcasterUserLogin() {
        return broadcasterUserLogin;
    }

    public void setBroadcasterUserLogin(String broadcasterUserLogin) {
        this.broadcasterUserLogin = broadcasterUserLogin;
    }

    public String getChatterUserId() {
        return chatterUserId;
    }

    public void setChatterUserId(String chatterUserId) {
        this.chatterUserId = chatterUserId;
    }

    public String getChatterUserName() {
        return chatterUserName;
    }

    public void setChatterUserName(String chatterUserName) {
        this.chatterUserName = chatterUserName;
    }

    public boolean isChatterIsAnonymous() {
        return chatterIsAnonymous;
    }

    public void setChatterIsAnonymous(boolean chatterIsAnonymous) {
        this.chatterIsAnonymous = chatterIsAnonymous;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<Badge> getBadges() {
        return badges;
    }

    public void setBadges(List<Badge> badges) {
        this.badges = badges;
    }

    public String getSystemMessage() {
        return systemMessage;
    }

    public void setSystemMessage(String systemMessage) {
        this.systemMessage = systemMessage;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(String noticeType) {
        this.noticeType = noticeType;
    }

    public Sub getSub() {
        return sub;
    }

    public void setSub(Sub sub) {
        this.sub = sub;
    }

    public Resub getResub() {
        return resub;
    }

    public void setResub(Resub resub) {
        this.resub = resub;
    }

    public SubGift getSubGift() {
        return subGift;
    }

    public void setSubGift(SubGift subGift) {
        this.subGift = subGift;
    }

    public CommunitySubGift getCommunitySubGift() {
        return communitySubGift;
    }

    public void setCommunitySubGift(CommunitySubGift communitySubGift) {
        this.communitySubGift = communitySubGift;
    }

    public GiftPaidUpgrade getGiftPaidUpgrade() {
        return giftPaidUpgrade;
    }

    public void setGiftPaidUpgrade(GiftPaidUpgrade giftPaidUpgrade) {
        this.giftPaidUpgrade = giftPaidUpgrade;
    }

    public PrimePaidUpgrade getPrimePaidUpgrade() {
        return primePaidUpgrade;
    }

    public void setPrimePaidUpgrade(PrimePaidUpgrade primePaidUpgrade) {
        this.primePaidUpgrade = primePaidUpgrade;
    }

    public PayItForward getPayItForward() {
        return payItForward;
    }

    public void setPayItForward(PayItForward payItForward) {
        this.payItForward = payItForward;
    }

    public Raid getRaid() {
        return raid;
    }

    public void setRaid(Raid raid) {
        this.raid = raid;
    }

    public Unraid getUnraid() {
        return unraid;
    }

    public void setUnraid(Unraid unraid) {
        this.unraid = unraid;
    }

    public Announcement getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(Announcement announcement) {
        this.announcement = announcement;
    }

    public BitsBadgeTier getBitsBadgeTier() {
        return bitsBadgeTier;
    }

    public void setBitsBadgeTier(BitsBadgeTier bitsBadgeTier) {
        this.bitsBadgeTier = bitsBadgeTier;
    }

    public CharityDonation getCharityDonation() {
        return charityDonation;
    }

    public void setCharityDonation(CharityDonation charityDonation) {
        this.charityDonation = charityDonation;
    }

    public String getSourceBroadcasterUserId() {
        return sourceBroadcasterUserId;
    }

    public void setSourceBroadcasterUserId(String sourceBroadcasterUserId) {
        this.sourceBroadcasterUserId = sourceBroadcasterUserId;
    }

    public String getSourceBroadcasterUserName() {
        return sourceBroadcasterUserName;
    }

    public void setSourceBroadcasterUserName(String sourceBroadcasterUserName) {
        this.sourceBroadcasterUserName = sourceBroadcasterUserName;
    }

    public String getSourceBroadcasterUserLogin() {
        return sourceBroadcasterUserLogin;
    }

    public void setSourceBroadcasterUserLogin(String sourceBroadcasterUserLogin) {
        this.sourceBroadcasterUserLogin = sourceBroadcasterUserLogin;
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

    public SharedChatSub getSharedChatSub() {
        return sharedChatSub;
    }

    public void setSharedChatSub(SharedChatSub sharedChatSub) {
        this.sharedChatSub = sharedChatSub;
    }

    public SharedChatResub getSharedChatResub() {
        return sharedChatResub;
    }

    public void setSharedChatResub(SharedChatResub sharedChatResub) {
        this.sharedChatResub = sharedChatResub;
    }

    public SharedChatSubGift getSharedChatSubGift() {
        return sharedChatSubGift;
    }

    public void setSharedChatSubGift(SharedChatSubGift sharedChatSubGift) {
        this.sharedChatSubGift = sharedChatSubGift;
    }

    public SharedChatCommunitySubGift getSharedChatCommunitySubGift() {
        return sharedChatCommunitySubGift;
    }

    public void setSharedChatCommunitySubGift(SharedChatCommunitySubGift sharedChatCommunitySubGift) {
        this.sharedChatCommunitySubGift = sharedChatCommunitySubGift;
    }

    public SharedChatGiftPaidUpgrade getSharedChatGiftPaidUpgrade() {
        return sharedChatGiftPaidUpgrade;
    }

    public void setSharedChatGiftPaidUpgrade(SharedChatGiftPaidUpgrade sharedChatGiftPaidUpgrade) {
        this.sharedChatGiftPaidUpgrade = sharedChatGiftPaidUpgrade;
    }

    public SharedChatPrimePaidUpgrade getSharedChatPrimePaidUpgrade() {
        return sharedChatPrimePaidUpgrade;
    }

    public void setSharedChatPrimePaidUpgrade(SharedChatPrimePaidUpgrade sharedChatPrimePaidUpgrade) {
        this.sharedChatPrimePaidUpgrade = sharedChatPrimePaidUpgrade;
    }

    public SharedChatPayItForward getSharedChatPayItForward() {
        return sharedChatPayItForward;
    }

    public void setSharedChatPayItForward(SharedChatPayItForward sharedChatPayItForward) {
        this.sharedChatPayItForward = sharedChatPayItForward;
    }

    public SharedChatRaid getSharedChatRaid() {
        return sharedChatRaid;
    }

    public void setSharedChatRaid(SharedChatRaid sharedChatRaid) {
        this.sharedChatRaid = sharedChatRaid;
    }

    public SharedChatAnnouncement getSharedChatAnnouncement() {
        return sharedChatAnnouncement;
    }

    public void setSharedChatAnnouncement(SharedChatAnnouncement sharedChatAnnouncement) {
        this.sharedChatAnnouncement = sharedChatAnnouncement;
    }

    
}
