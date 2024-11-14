package com.motyldrogi.bot.notification;

import java.util.Optional;

import com.motyldrogi.bot.configuration.AppProperties;
import com.motyldrogi.bot.entity.TwitchWebSocketMessage.event.MessageEvent;
import com.motyldrogi.bot.entity.impl.UserEntityImpl;
import com.motyldrogi.bot.repository.UserRepository;
import com.motyldrogi.bot.service.CommandProcessorService;
import com.motyldrogi.bot.util.Role;

public class MessageNotificationHandler implements EventNotificationHandler<MessageEvent> {

    private final CommandProcessorService commandProcessorService;
    private final UserRepository userRepository;
    private final AppProperties properties;

    public MessageNotificationHandler(
        CommandProcessorService commandProcessorService,
        AppProperties properties,
        UserRepository userRepository
        ) {
        this.commandProcessorService = commandProcessorService;
        this.properties = properties;
        this.userRepository = userRepository;
    }

    @Override
    public void handleNotification(MessageEvent event) {
        System.out.println("Message from : " + event.getChatterUserName() + ", Text : " + event.getMessage().getText());
        UserEntityImpl user = null;
        Optional<UserEntityImpl> optionalUserEntity = userRepository.findByTwitchId(event.getChatterUserId());

        if (!optionalUserEntity.isPresent()){
            user = new UserEntityImpl.Builder()
                .withIdentifier(null)
                .withTwitchId(event.getChatterUserId())
                .withName(event.getChatterUserName())
                .withRole(Role.VIEWER)
                .withNumberMessagesSent(1)
                .withCounter(0)
                .withChatColor(event.getColor())
                .build();

                user = userRepository.save(user);
        }

        if (user == null){
            user = optionalUserEntity.get();
        }

        int numberMessagesSent = user.getNumberMessagesSent();
        numberMessagesSent++;

        user.setNumberMessagesSent(numberMessagesSent);
        userRepository.save(user);

        String message = event.getMessage().getText();

        if (message.startsWith(properties.getPrefix())) {
            commandProcessorService.processCommand(message.replace(properties.getPrefix(),""), user);
        }
    }

}
