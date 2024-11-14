package com.motyldrogi.bot.command;

import com.motyldrogi.bot.command.defaults.CommandExecutor;
import com.motyldrogi.bot.command.defaults.CommandInfo;
import com.motyldrogi.bot.entity.impl.UserEntityImpl;
import com.motyldrogi.bot.service.TwitchApiService;

public class NumberMessagesSentCommand implements CommandExecutor {

    public NumberMessagesSentCommand(){

    }
    
    @CommandInfo(value = "messages", description = "Number of messages you sent on this channel")
    @Override
    public void execute(TwitchApiService twitchApiService, String commandString, UserEntityImpl user){
        twitchApiService.sendMessage("@" + user.getName() + " you have sent " + user.getNumberMessagesSent() + " messages !");
    }
    
}
