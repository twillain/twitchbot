package com.motyldrogi.bot.command;

import com.motyldrogi.bot.command.defaults.CommandExecutor;
import com.motyldrogi.bot.command.defaults.CommandInfo;
import com.motyldrogi.bot.service.TwitchApiService;
import com.motyldrogi.bot.user.UserEntity;
import com.motyldrogi.bot.user.UserService;


public class CounterCommand implements CommandExecutor {
    
    private final TwitchApiService twitchApiService;
    private final UserService userService;

    public CounterCommand(TwitchApiService twitchApiService, UserService userService) {
      this.twitchApiService = twitchApiService;
      this.userService = userService;
    }

    @CommandInfo(value = "counter", description = "Increments by 1 your counter")
    @Override
    public void execute(CommandParser command, UserEntity user) {
        

        Integer counter = user.getCounterValue();
        counter++;

        // Update value +1
        user.setCounterValue(counter);
        userService.saveUser(user);
        
        twitchApiService.sendMessage("@" + user.getName() + " Your counter is currently at " + counter);
    }
}
