package com.motyldrogi.bot.command.defaults;

import com.motyldrogi.bot.entity.impl.UserEntityImpl;
import com.motyldrogi.bot.service.TwitchApiService;

public interface CommandExecutor {

    void execute(TwitchApiService twitchApiService, String commandString, UserEntityImpl user);
}
