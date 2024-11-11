package com.motyldrogi.bot.command;

import com.motyldrogi.bot.command.defaults.CommandExecutor;
import com.motyldrogi.bot.command.defaults.CommandInfo;
import com.motyldrogi.bot.command.defaults.CommandSender;
import com.motyldrogi.bot.component.TwitchMessage;
import com.motyldrogi.bot.entity.impl.AutomatedMessageEntityImpl;
import com.motyldrogi.bot.entity.impl.UserEntityImpl;
import com.motyldrogi.bot.service.AutomatedMessageService;
import com.motyldrogi.bot.util.Role;

public class AutomatedMessagesCommand implements CommandExecutor {

    private AutomatedMessageService automatedMessageService;

    public AutomatedMessagesCommand(AutomatedMessageService automatedMessageService){
        this.automatedMessageService = automatedMessageService;
    }

    @CommandInfo(value = "automatedMessage", minArguments = 1, maxArguments = 250 ,usage = "<add|remove|all> <time(min)> <message>" , role = Role.MODERATOR, description = "Set an automated message to be sent every x minutes")
    public void execute(TwitchMessage tMessage, CommandSender commandSender, UserEntityImpl user){


        String[] data = tMessage.getData().split(" ");

        if (data[0].toLowerCase().equals("add")){

            Long time = Long.valueOf("0");

            try {
                time = Long.valueOf(data[1]);
            } catch (NumberFormatException e){
                System.err.println(e.getMessage());
            }

            if (time != 0){
                AutomatedMessageEntityImpl automatedMessage = new AutomatedMessageEntityImpl.Builder()
                    .withIdentifier(null)
                    .withMessage(tMessage.getData().substring(getIndexStartingTextAfterSpaces(tMessage.getData(), 2)))
                    .withTimerDelay(time)
                    .withTimerPeriod(time)
                    .build();

                automatedMessageService.addAutomatedMessage(automatedMessage);
                commandSender.sendRawMessage("@" + tMessage.getSentBy() + " Automated message added !");
            }

        } else if (data[0].toLowerCase().equals("remove")){

            Long identifier = Long.valueOf("0");

            try {
                identifier = Long.valueOf(data[1]);
            } catch (NumberFormatException e){
                System.err.println(e.getMessage());
            }
            automatedMessageService.removeAutomatedMessageById(identifier);
            commandSender.sendRawMessage("@" + tMessage.getSentBy() + " Automated message removed !");

        } else if (data[0].toLowerCase().equals("all")) {
            automatedMessageService.getAllAutomatedMessages().forEach((message) -> {
                commandSender.sendRawMessage("ID: " + message.getIdentifier() + " - " + message.getMessage());
            });
        }else {
            commandSender.sendRawMessage("@" + tMessage.getSentBy() + " Invalid command, valid : add, remove");
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
