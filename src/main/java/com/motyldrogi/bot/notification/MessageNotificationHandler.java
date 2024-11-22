package com.motyldrogi.bot.notification;

import com.motyldrogi.bot.command.CommandParser;
import com.motyldrogi.bot.configuration.AppProperties;
import com.motyldrogi.bot.entity.TwitchWebSocketMessage.event.MessageEvent;
import com.motyldrogi.bot.entity.TwitchWebSocketMessage.event.eventUtils.Payload;
import com.motyldrogi.bot.service.CommandProcessorService;
import com.motyldrogi.bot.user.UserEntity;
import com.motyldrogi.bot.user.UserService;

public class MessageNotificationHandler implements EventNotificationHandler<MessageEvent> {

    private final CommandProcessorService commandProcessorService;
    private final UserService userService;
    private final AppProperties properties;

    public MessageNotificationHandler(
        CommandProcessorService commandProcessorService,
        UserService userService,
        AppProperties properties
        ) {
        this.commandProcessorService = commandProcessorService;
        this.userService = userService;
        this.properties = properties;
    }

    @Override
    public void handleNotification(Payload payload) {

        MessageEvent event = (MessageEvent) payload.getEvent();

        System.out.println("Message from : " + event.getUserName() + ", Text : " + event.getMessage().getText());

        UserEntity user = userService.getUserByEventOrCreateEntity(event);
        if (user == null) throw new IllegalArgumentException("No user found with ID : " + event.getUserId());

        user.setChatColor(event.getColor());

        int numberMessagesSent = user.getNumberMessagesSent();
        numberMessagesSent++;

        user.setNumberMessagesSent(numberMessagesSent);
        userService.saveUser(user);

        String message = event.getMessage().getText();
        try {
            

            if (message.startsWith(properties.getPrefix())) {
                CommandParser command = new CommandParser(message);
                commandProcessorService.processCommand(command, user);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error parsing command : " + e.getMessage());
        }

        
    }

}
