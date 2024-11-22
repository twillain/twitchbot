package com.motyldrogi.bot.user;

import org.springframework.stereotype.Service;

import com.motyldrogi.bot.entity.TwitchWebSocketMessage.event.Event;
import com.motyldrogi.bot.role.entity.Role;

@Service
public class UserService {

    private final UserRepository userRepository;
    
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity getUserByTwitchId(String twitchId){
        return userRepository.findById(Long.parseLong(twitchId)).orElse(null);
    }

    public UserEntity getUserByTwitchId(Long twitchId){
        return userRepository.findById(twitchId).orElse(null);
    }

    public UserEntity saveUser(UserEntity user){
        return userRepository.save(user);
    }

    public UserEntity getUserByName(String username){
        return userRepository.findByName(username).orElse(null);
    }

    public UserEntity getUserByEventOrCreateEntity(Event event){
        UserEntity userEntity = this.getUserByTwitchId(event.getUserId());

        if (userEntity == null) {
            userEntity = new UserEntity.Builder()
                .withId(event.getUserId())
                .withName(event.getUserName())
                .withRole(Role.VIEWER)
                .withNumberMessagesSent(0)
                .withCounter(0)
                .build();

            userEntity = this.saveUser(userEntity);
        }
        return userEntity;
    }

}
