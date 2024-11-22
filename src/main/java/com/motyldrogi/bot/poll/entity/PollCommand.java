package com.motyldrogi.bot.poll.entity;

import java.util.Arrays;

import com.motyldrogi.bot.command.CommandParser;
import com.motyldrogi.bot.command.defaults.CommandExecutor;
import com.motyldrogi.bot.command.defaults.CommandInfo;
import com.motyldrogi.bot.poll.PollService;
import com.motyldrogi.bot.service.TwitchApiService;
import com.motyldrogi.bot.user.UserEntity;

public class PollCommand implements CommandExecutor {

    private final TwitchApiService twitchApiService;
    private final PollService pollService;

    public PollCommand(TwitchApiService twitchApiService, PollService pollService){
        this.twitchApiService = twitchApiService;
        this.pollService = pollService;
    }

    @CommandInfo(value = "poll", minArguments = 0, maxArguments = 10)
    @Override
    public void execute(CommandParser commandParser, UserEntity user) {
        String[] args = commandParser.getArgs();
        // !poll - Get current poll
        if (args.length == 0){
            PollResponseEntity currentPoll = pollService.getCurrentPoll();
            if (currentPoll == null){
                twitchApiService.sendMessage("No current poll");
                return;
            } 
            twitchApiService.sendMessage("Current poll - ID: %s, Title: "+  currentPoll.getTitle());
            return;
        }

        // !poll new 1 title duration pointsParVote choice1 choice2 ... choicex
        if (args[0].equals("new") && args[1].equals("1")){
            PollResponseEntity currentPoll = pollService.createPollWithChannelPoints(args[2], args[3], args[4], Arrays.copyOfRange(args, args.length-4, args.length));
            twitchApiService.sendAnnouncement(String.format("Vote to the current poll ! Poll Title: %s, Cost per vote: %s", currentPoll.getTitle(), args[4]));
        }
        // !poll new 0 title duration choice1 choice2 ... choicex
        if (args[0].equals("new") && args[1].equals("0")){
            PollResponseEntity currentPoll = pollService.createPollWithoutChannelPoints(args[2], args[3], Arrays.copyOfRange(args, args.length-2, args.length));
            twitchApiService.sendAnnouncement(String.format("Vote to the current poll ! Poll Title: %s", currentPoll.getTitle()));
        }

        // !poll end
        if (args[0].equals("end")){
            PollChoiceEntity winningChoice = pollService.endCurrentPoll(PollStatusEntity.TERMINATED);
            twitchApiService.sendAnnouncement(String.format("Choice %s win with %s channel points used !",winningChoice.getTitle(), winningChoice.getChannelPointsVotes()));
        }

        // !poll delete
        if (args[0].equals("delete")){
            pollService.endCurrentPoll(PollStatusEntity.ARCHIVED);
        }

    }
    
}
