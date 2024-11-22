package com.motyldrogi.bot.command;

import com.motyldrogi.bot.automatedMessages.AutomatedMessageEntity;
import com.motyldrogi.bot.automatedMessages.AutomatedMessageService;
import com.motyldrogi.bot.command.defaults.CommandExecutor;
import com.motyldrogi.bot.command.defaults.CommandInfo;
import com.motyldrogi.bot.role.entity.Role;
import com.motyldrogi.bot.service.TwitchApiService;
import com.motyldrogi.bot.user.UserEntity;

public class AutomatedMessagesCommand implements CommandExecutor {

    private AutomatedMessageService automatedMessageService;
    private TwitchApiService twitchApiService;

    public AutomatedMessagesCommand(TwitchApiService twitchApiService, AutomatedMessageService automatedMessageService){
        this.twitchApiService = twitchApiService;
        this.automatedMessageService = automatedMessageService;
    }

    @CommandInfo(value = "automatedMessage", minArguments = 1, maxArguments = 3 ,usage = "<add|remove|all> <time(min)> <message>" , role = Role.MODERATOR, description = "Set an automated message to be sent every x minutes")
    public void execute(CommandParser command, UserEntity user) {


        if (command.getArgs()[0].toLowerCase().equals("add")){

            Long time = Long.valueOf("0");

            try {
                time = Long.valueOf(command.getArgs()[1]);
            } catch (NumberFormatException e){
                System.err.println(e.getMessage());
            }

            if (time != 0){
                AutomatedMessageEntity automatedMessage = new AutomatedMessageEntity.Builder()
                    .withIdentifier(null)
                    .withMessage(command.getArgs()[2])
                    //TODO Check for non-negative time
                    .withTimerDelay(time)
                    .withTimerPeriod(time)
                    .build();

                automatedMessageService.addAutomatedMessage(automatedMessage);
                twitchApiService.sendMessage("@" + user.getName() + " Automated message added !");
            }

        } else if (command.getArgs()[0].toLowerCase().equals("remove")){

            Long identifier = Long.valueOf("0");
            //TODO Check for existing identifier
            try {
                identifier = Long.valueOf(command.getArgs()[1]);
            } catch (NumberFormatException e){
                System.err.println(e.getMessage());
            }
            automatedMessageService.removeAutomatedMessageById(identifier);
            twitchApiService.sendMessage("@" + user.getName() + " Automated message removed !");

        } else if (command.getArgs()[0].toLowerCase().equals("all")) {
            automatedMessageService.getAllAutomatedMessages().forEach((message) -> {
                twitchApiService.sendMessage("ID: " + message.getIdentifier() + " - " + message.getMessage());
            });
        }else {
            twitchApiService.sendMessage("@" + user.getName() + " Invalid command, valid : add, remove");
        }
    }
    
}
