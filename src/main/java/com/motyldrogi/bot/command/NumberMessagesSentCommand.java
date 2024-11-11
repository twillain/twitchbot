package com.motyldrogi.bot.command;

import com.motyldrogi.bot.command.defaults.CommandExecutor;
import com.motyldrogi.bot.command.defaults.CommandInfo;
import com.motyldrogi.bot.command.defaults.CommandSender;
import com.motyldrogi.bot.component.TwitchMessage;
import com.motyldrogi.bot.entity.impl.UserEntityImpl;

public class NumberMessagesSentCommand implements CommandExecutor {

    public NumberMessagesSentCommand(){

    }
    
    @CommandInfo(value = "messages", description = "Number of messages you sent on this channel")
    @Override
    public void execute(TwitchMessage tMessage, CommandSender commandSender, UserEntityImpl userEntity){
        commandSender.sendRawMessage("@" + tMessage.getSentBy() + " you have sent " + userEntity.getNumberMessagesSent() + " messages !");
    }
    
}
