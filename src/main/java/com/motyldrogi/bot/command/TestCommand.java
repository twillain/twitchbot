package com.motyldrogi.bot.command;

import com.motyldrogi.bot.command.defaults.CommandExecutor;
import com.motyldrogi.bot.command.defaults.CommandInfo;
import com.motyldrogi.bot.command.defaults.CommandSender;
import com.motyldrogi.bot.component.TwitchMessage;
import com.motyldrogi.bot.entity.impl.UserEntityImpl;

public class TestCommand implements CommandExecutor {
    @CommandInfo(value = "test", description = "For testing purposes")
    @Override
    public void execute(TwitchMessage tMessage, CommandSender commandSender, UserEntityImpl userEntity) {
        commandSender.sendRawMessage("Result of test command : " + tMessage.getData() + "tMessage infos : " + tMessage.getMessage());        
    }

    
}
