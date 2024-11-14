package com.motyldrogi.bot.command;

import com.motyldrogi.bot.command.defaults.CommandExecutor;
import com.motyldrogi.bot.command.defaults.CommandInfo;
import com.motyldrogi.bot.entity.impl.UserEntityImpl;
import com.motyldrogi.bot.service.TwitchApiService;

public class TestCommand implements CommandExecutor {
    @CommandInfo(value = "test", description = "For testing purposes")
    @Override
    public void execute(TwitchApiService twitchApiService, String commandString, UserEntityImpl user) {
        twitchApiService.createPrediction("Ceci est un test", "30", "choice1","choice2");        
    }

    
}
