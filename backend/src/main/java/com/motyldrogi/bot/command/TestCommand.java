package com.motyldrogi.bot.command;

import java.util.List;

import com.motyldrogi.bot.TwitchApi.TwitchApiService;
import com.motyldrogi.bot.command.defaults.CommandExecutor;
import com.motyldrogi.bot.command.defaults.CommandInfo;
import com.motyldrogi.bot.poll.PollService;
import com.motyldrogi.bot.poll.entity.PollResponseEntity;
import com.motyldrogi.bot.poll.entity.PollStatusEntity;
import com.motyldrogi.bot.stream.entity.StreamService;
import com.motyldrogi.bot.user.UserEntity;

public class TestCommand implements CommandExecutor {

    private final TwitchApiService twitchApiService;
    private final StreamService streamInfoService;

    public TestCommand(TwitchApiService twitchApiService, StreamService streamInfoService){
        this.twitchApiService = twitchApiService;
        this.streamInfoService = streamInfoService;
    }
    @CommandInfo(value = "test", description = "For testing purposes")
    @Override
    public void execute(CommandParser commandParser, UserEntity userEntity) {
        //twitchApiService.sendMessage(streamInfoService.getStreamInfo().getTitle());
    }

    
}
