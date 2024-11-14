package com.motyldrogi.bot.command;

import java.util.Random;

import com.motyldrogi.bot.command.defaults.CommandExecutor;
import com.motyldrogi.bot.command.defaults.CommandInfo;
import com.motyldrogi.bot.entity.impl.UserEntityImpl;
import com.motyldrogi.bot.service.TwitchApiService;

public class DiceCommand implements CommandExecutor {
    
    private Random rand;

    public DiceCommand() {
        this.rand = new Random(); 
    }

    @CommandInfo(value = "dice", description = "Throw a dice")
    @Override
    public void execute(TwitchApiService twitchApiService, String commandString, UserEntityImpl user) {
        
        twitchApiService.sendMessage("@" + user.getName() + " You rolled: " + (rand.nextInt(6) + 1));
    }
}
