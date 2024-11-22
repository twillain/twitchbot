package com.motyldrogi.bot.command.defaults;

import com.motyldrogi.bot.command.CommandParser;
import com.motyldrogi.bot.user.UserEntity;

public interface CommandExecutor {

    void execute(CommandParser commandParser, UserEntity user);
}
