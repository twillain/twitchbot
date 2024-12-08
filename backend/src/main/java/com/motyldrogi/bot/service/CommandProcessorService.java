package com.motyldrogi.bot.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.motyldrogi.bot.TwitchApi.TwitchApiService;
import com.motyldrogi.bot.command.CommandParser;
import com.motyldrogi.bot.command.defaults.Command;
import com.motyldrogi.bot.component.MessageComponent;
import com.motyldrogi.bot.configuration.AppProperties;
import com.motyldrogi.bot.role.entity.Role;
import com.motyldrogi.bot.user.UserEntity;

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

    public void processCommand(CommandParser command, UserEntity user) {
        System.out.println(command);
        if (command.getCommand().equals("commands")){
            commandRegistry.forEach((k,v) -> twitchApiService.sendMessage("Command : !" + k + ' ' + v.getUsage() + " - " + v.getDescription()));
        }

        if (this.commandRegistry.keySet().contains(command.getCommand())) {
            Command botCommand = this.commandRegistry.get(command.getCommand());

            if (botCommand == null) {
                twitchApiService.sendMessage("Command " + command + " not found !");
                return;
            }

            if (user.getRole().getLevel() < botCommand.getRole().getLevel() && user.getRole() != Role.ADMIN){
                String invalidMessage = messageComponent.get("invalid-role");
                twitchApiService.sendMessage(invalidMessage);
                return;
            }

            if ((command.getArgs().length < botCommand.getMinArguments()) || (command.getArgs().length > botCommand.getMaxArguments())) {
                String usage = properties.getPrefix() + botCommand.getName() + " " + botCommand.getUsage();
                String invalidMessage = messageComponent.get("invalid-pattern", usage);
                twitchApiService.sendMessage(invalidMessage);
                return;
            }

            botCommand.getExecutor().execute(command, user);
        } else {
            System.err.println("No such command  : " + command.toString());
        }

        
    }

    public Map<String, Command> getAllCommands(){
        return this.commandRegistry;
    }
}
