package com.motyldrogi.bot.command.defaults;

import com.motyldrogi.bot.component.TwitchMessage;
import com.motyldrogi.bot.entity.impl.UserEntityImpl;

public interface CommandExecutor {

    void execute(TwitchMessage tMessage, CommandSender commandSender, UserEntityImpl userEntity);
}
