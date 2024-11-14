package com.motyldrogi.bot.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.motyldrogi.bot.command.defaults.Command;
import com.motyldrogi.bot.component.MessageComponent;
import com.motyldrogi.bot.configuration.AppProperties;
import com.motyldrogi.bot.entity.impl.UserEntityImpl;
import com.motyldrogi.bot.util.Role;

@Service
public class CommandProcessorService { 

    private final TwitchApiService twitchApiService;
    private final MessageComponent messageComponent;
    private final AppProperties properties;

    private Map<String, Command> commandRegistry = new HashMap<>();
    
    public CommandProcessorService(
        TwitchApiService twitchApiService,
        MessageComponent messageComponent,
        AppProperties properties
        ) {
        this.twitchApiService = twitchApiService;
        this.messageComponent = messageComponent;
        this.properties = properties;
    }


    public void registerCommand(String command, Command botCommand){
        this.commandRegistry.put(command.toLowerCase(), botCommand);
    }

    public void processCommand(String commandString, UserEntityImpl user) {
        if (commandString.equals("commands")){
            commandRegistry.forEach((k,v) -> twitchApiService.sendMessage("Command : !" + k + ' ' + v.getUsage() + " - " + v.getDescription()));
        }
        String command = commandString.split(" ")[0];
        if (this.commandRegistry.keySet().contains(command)) {
            Command botCommand = this.commandRegistry.get(command.toLowerCase());

            if (botCommand == null){
                twitchApiService.sendMessage("Command " + command + " not found !");
                return;
            }

            if (user.getRole().getLevel() < botCommand.getRole().getLevel() && user.getRole() != Role.ADMIN){
                String invalidMessage = messageComponent.get("invalid-role");
                twitchApiService.sendMessage(invalidMessage);
                return;
            }

            List<String> args = Arrays.stream(commandString.split(" ")).skip(1)
                    .collect(Collectors.toList());
                    
            if ((args.size() < botCommand.getMinArguments()) || (args.size() > botCommand.getMaxArguments())) {
                String usage = properties.getPrefix() + botCommand.getName() + " " + botCommand.getUsage();
                String invalidMessage = messageComponent.get("invalid-pattern", usage);
                twitchApiService.sendMessage(invalidMessage);
                return;
            }

            botCommand.getExecutor().execute(twitchApiService, commandString, user);
        }
    }
}
