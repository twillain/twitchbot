package com.motyldrogi.bot.command;

import java.util.List;

import com.motyldrogi.bot.command.defaults.CommandExecutor;
import com.motyldrogi.bot.command.defaults.CommandInfo;
import com.motyldrogi.bot.poll.PollService;
import com.motyldrogi.bot.poll.entity.PollResponseEntity;
import com.motyldrogi.bot.poll.entity.PollStatusEntity;
import com.motyldrogi.bot.service.TwitchApiService;
import com.motyldrogi.bot.user.UserEntity;

public class TestCommand implements CommandExecutor {

    private final TwitchApiService twitchApiService;
    private final PollService pollService;

    public TestCommand(TwitchApiService twitchApiService, PollService pollService){
        this.twitchApiService = twitchApiService;
        this.pollService = pollService;
    }
    @CommandInfo(value = "test", description = "For testing purposes")
    @Override
    public void execute(CommandParser commandParser, UserEntity userEntity) {
        pollService.createPollWithoutChannelPoints("Test4", 60, "Choix1","Choix2");
        //pollService.endCurrentPoll(PollStatusEntity.TERMINATED);
    }

    
}
