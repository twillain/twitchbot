package com.motyldrogi.bot.command;

import com.motyldrogi.bot.command.defaults.CommandExecutor;
import com.motyldrogi.bot.command.defaults.CommandInfo;
import com.motyldrogi.bot.command.defaults.CommandSender;
import com.motyldrogi.bot.component.TwitchMessage;
import com.motyldrogi.bot.entity.impl.UserEntityImpl;
import com.motyldrogi.bot.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class CounterCommand implements CommandExecutor {
    
    private final UserRepository userRepository;

    @Autowired
    public CounterCommand(UserRepository userRepository) {
      this.userRepository = userRepository;
    }

    public CounterCommand() {
        this(null);
    }

    @CommandInfo(value = "counter", description = "Increments by 1 your counter")
    @Override
    public void execute(TwitchMessage tMessage, CommandSender commandSender, UserEntityImpl userEntity) {
        

        Integer counter = userEntity.getCounterValue();
        counter++;

        // Update value +1
        userEntity.setCounterValue(counter);
        userRepository.save(userEntity);
        
        commandSender.sendRawMessage("@" + tMessage.getSentBy() + " Your counter is currently at " + counter);
    }
}
