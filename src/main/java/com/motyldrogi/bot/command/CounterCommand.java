package com.motyldrogi.bot.command;

import com.motyldrogi.bot.command.defaults.CommandExecutor;
import com.motyldrogi.bot.command.defaults.CommandInfo;
import com.motyldrogi.bot.entity.impl.UserEntityImpl;
import com.motyldrogi.bot.repository.UserRepository;
import com.motyldrogi.bot.service.TwitchApiService;


public class CounterCommand implements CommandExecutor {
    
    private final UserRepository userRepository;

    public CounterCommand(UserRepository userRepository) {
      this.userRepository = userRepository;
    }

    @CommandInfo(value = "counter", description = "Increments by 1 your counter")
    @Override
    public void execute(TwitchApiService twitchApiService, String commandString, UserEntityImpl user) {
        

        Integer counter = user.getCounterValue();
        counter++;

        // Update value +1
        user.setCounterValue(counter);
        userRepository.save(user);
        
        twitchApiService.sendMessage("@" + user.getName() + " Your counter is currently at " + counter);
    }
}
