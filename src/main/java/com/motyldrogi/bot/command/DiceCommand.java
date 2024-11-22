package com.motyldrogi.bot.command;

import java.util.Random;

import com.motyldrogi.bot.command.defaults.CommandExecutor;
import com.motyldrogi.bot.command.defaults.CommandInfo;
import com.motyldrogi.bot.service.TwitchApiService;
import com.motyldrogi.bot.user.UserEntity;

public class DiceCommand implements CommandExecutor {
    
    private final TwitchApiService twitchApiService;
    private Random rand;

    public DiceCommand(TwitchApiService twitchApiService) {
        this.twitchApiService = twitchApiService;
        this.rand = new Random(); 
    }

    @CommandInfo(value = "dice", description = "Throw a dice")
    @Override
    public void execute(CommandParser command, UserEntity user) {
        
        twitchApiService.sendMessage("@" + user.getName() + " You rolled: " + (rand.nextInt(6) + 1));
    }
}
