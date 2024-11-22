package com.motyldrogi.bot.command;

import com.motyldrogi.bot.command.defaults.CommandExecutor;
import com.motyldrogi.bot.command.defaults.CommandInfo;
import com.motyldrogi.bot.service.TwitchApiService;
import com.motyldrogi.bot.user.UserEntity;

public class NumberMessagesSentCommand implements CommandExecutor {

    private final TwitchApiService twitchApiService;

    public NumberMessagesSentCommand(TwitchApiService twitchApiService){
        this.twitchApiService = twitchApiService;
    }
    
    @CommandInfo(value = "messages", description = "Number of messages you sent on this channel")
    @Override
    public void execute(CommandParser command, UserEntity user){
        twitchApiService.sendMessage("@" + user.getName() + " you have sent " + user.getNumberMessagesSent() + " messages !");
    }
    
}
