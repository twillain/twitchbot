package com.motyldrogi.bot.command;

import com.motyldrogi.bot.TwitchApi.TwitchApiService;
import com.motyldrogi.bot.command.defaults.CommandExecutor;
import com.motyldrogi.bot.command.defaults.CommandInfo;
import com.motyldrogi.bot.user.UserEntity;

public class AnnouncementCommand implements CommandExecutor{
    
    private final TwitchApiService twitchApiService;

    public AnnouncementCommand(TwitchApiService twitchApiService){
        this.twitchApiService = twitchApiService;
    }

    @CommandInfo(value = "announce", minArguments = 1, maxArguments = 1 ,usage = "[message]", description = "Send announcement")
    @Override
    public void execute(CommandParser command, UserEntity user){
        twitchApiService.sendAnnouncement(command.getArgs()[0]);
    }
}
