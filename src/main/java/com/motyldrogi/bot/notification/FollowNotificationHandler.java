package com.motyldrogi.bot.notification;

import com.motyldrogi.bot.entity.TwitchWebSocketMessage.event.FollowEvent;
import com.motyldrogi.bot.entity.TwitchWebSocketMessage.event.eventUtils.Payload;
import com.motyldrogi.bot.service.TwitchApiService;
import com.motyldrogi.bot.user.UserEntity;
import com.motyldrogi.bot.user.UserService;

public class FollowNotificationHandler implements EventNotificationHandler<FollowEvent> {
    
    private final TwitchApiService twitchApiService;
    private final UserService userService;

    public FollowNotificationHandler(TwitchApiService twitchApiService, UserService userService){
        this.twitchApiService = twitchApiService;
        this.userService = userService;
    }

    @Override
    public void handleNotification(Payload payload) {

        FollowEvent event = (FollowEvent) payload.getEvent();
        
        UserEntity user = userService.getUserByEventOrCreateEntity(event);
        if (user == null) throw new IllegalArgumentException("No user found with ID : " + event.getUserId());

        user.setFollowedAt(event.getFollowedAt());
        userService.saveUser(user);

        twitchApiService.sendAnnouncement("Thank you for the follow @" + event.getUserName() + " !");
        
    }
}
