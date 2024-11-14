package com.motyldrogi.bot.command;

import com.motyldrogi.bot.command.defaults.CommandExecutor;
import com.motyldrogi.bot.command.defaults.CommandInfo;
import com.motyldrogi.bot.entity.impl.AutomatedMessageEntityImpl;
import com.motyldrogi.bot.entity.impl.UserEntityImpl;
import com.motyldrogi.bot.service.AutomatedMessageService;
import com.motyldrogi.bot.service.TwitchApiService;
import com.motyldrogi.bot.util.Role;

public class AutomatedMessagesCommand implements CommandExecutor {

    private AutomatedMessageService automatedMessageService;

    public AutomatedMessagesCommand(AutomatedMessageService automatedMessageService){
        this.automatedMessageService = automatedMessageService;
    }

    @CommandInfo(value = "automatedMessage", minArguments = 1, maxArguments = 250 ,usage = "<add|remove|all> <time(min)> <message>" , role = Role.MODERATOR, description = "Set an automated message to be sent every x minutes")
    public void execute(TwitchApiService twitchApiService, String commandString, UserEntityImpl user) {


        String[] data = commandString.split(" ");

        if (data[1].toLowerCase().equals("add")){

            Long time = Long.valueOf("0");

            try {
                time = Long.valueOf(data[2]);
            } catch (NumberFormatException e){
                System.err.println(e.getMessage());
            }

            if (time != 0){
                AutomatedMessageEntityImpl automatedMessage = new AutomatedMessageEntityImpl.Builder()
                    .withIdentifier(null)
                    .withMessage(commandString.substring(getIndexStartingTextAfterSpaces(commandString, 3)))
                    //TODO Check for non-negative time
                    .withTimerDelay(time)
                    .withTimerPeriod(time)
                    .build();

                automatedMessageService.addAutomatedMessage(automatedMessage);
                twitchApiService.sendMessage("@" + user.getName() + " Automated message added !");
            }

        } else if (data[1].toLowerCase().equals("remove")){

            Long identifier = Long.valueOf("0");
            //TODO Check for existing identifier
            try {
                identifier = Long.valueOf(data[2]);
            } catch (NumberFormatException e){
                System.err.println(e.getMessage());
            }
            automatedMessageService.removeAutomatedMessageById(identifier);
            twitchApiService.sendMessage("@" + user.getName() + " Automated message removed !");

        } else if (data[1].toLowerCase().equals("all")) {
            automatedMessageService.getAllAutomatedMessages().forEach((message) -> {
                twitchApiService.sendMessage("ID: " + message.getIdentifier() + " - " + message.getMessage());
            });
        }else {
            twitchApiService.sendMessage("@" + user.getName() + " Invalid command, valid : add, remove");
        }
    }

    public static int getIndexStartingTextAfterSpaces(String s, int spaces){
        int space = 0;
        for (int i=0 ; i < s.length() ; i++){
            if (space == spaces) return i;
            if (s.charAt(i) == ' ') space++;
        }
        return -1;
    }
    
}
