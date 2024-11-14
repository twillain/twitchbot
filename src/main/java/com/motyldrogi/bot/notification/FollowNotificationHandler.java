package com.motyldrogi.bot.notification;

import java.util.Optional;

import com.motyldrogi.bot.entity.TwitchWebSocketMessage.event.FollowEvent;
import com.motyldrogi.bot.entity.impl.UserEntityImpl;
import com.motyldrogi.bot.repository.UserRepository;
import com.motyldrogi.bot.service.TwitchApiService;
import com.motyldrogi.bot.util.Role;

public class FollowNotificationHandler implements EventNotificationHandler<FollowEvent> {
    
    private final UserRepository userRepository;
    private final TwitchApiService twitchApiService;

    public FollowNotificationHandler(TwitchApiService twitchApiService, UserRepository userRepository){
        this.twitchApiService = twitchApiService;
        this.userRepository = userRepository;
    }

    @Override
    public void handleNotification(FollowEvent event) {
        
        UserEntityImpl user = null;
        Optional<UserEntityImpl> optionalUserEntity = userRepository.findByTwitchId(event.getUserId());

        if (!optionalUserEntity.isPresent()){
            user = new UserEntityImpl.Builder()
                .withIdentifier(null)
                .withTwitchId(event.getUserId())
                .withName(event.getUserName())
                .withRole(Role.VIEWER)
                .withNumberMessagesSent(1)
                .withCounter(0)
                .withFollowedAt(event.getFollowedAt())
                .build();

                user = userRepository.save(user);
        }

        if (user == null){
            user = optionalUserEntity.get();
            user.setFollowedAt(event.getFollowedAt());
            userRepository.save(user);
        }

        twitchApiService.sendAnnoucement("Thank you for the follow @" + event.getUserName() + " !");
        
    }
}
