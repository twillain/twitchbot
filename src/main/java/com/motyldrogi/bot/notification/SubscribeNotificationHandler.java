package com.motyldrogi.bot.notification;

import java.util.Optional;
import java.util.concurrent.Flow.Subscriber;

import com.motyldrogi.bot.entity.TwitchWebSocketMessage.event.SubscribeEvent;
import com.motyldrogi.bot.entity.impl.UserEntityImpl;
import com.motyldrogi.bot.repository.UserRepository;
import com.motyldrogi.bot.util.Role;

public class SubscribeNotificationHandler implements EventNotificationHandler<SubscribeEvent> {

    private final UserRepository userRepository;

    public SubscribeNotificationHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void handleNotification(SubscribeEvent event){
        UserEntityImpl user = null;
        Optional<UserEntityImpl> optionalUserEntity = userRepository.findByTwitchId(event.getUserId());

        if (!optionalUserEntity.isPresent()){
            user = new UserEntityImpl.Builder()
                .withIdentifier(null)
                .withTwitchId(event.getUserId())
                .withName(event.getUserName())
                .withRole(Role.VIEWER)
                .withNumberMessagesSent(0)
                .withCounter(0)
                .withSubscribedAt()
                .build();

                user = userRepository.save(user);
        }


    }
    
}
