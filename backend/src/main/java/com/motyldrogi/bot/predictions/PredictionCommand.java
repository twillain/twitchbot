package com.motyldrogi.bot.predictions;

import java.util.Arrays;

import com.motyldrogi.bot.TwitchApi.TwitchApiService;
import com.motyldrogi.bot.command.CommandParser;
import com.motyldrogi.bot.command.defaults.CommandExecutor;
import com.motyldrogi.bot.command.defaults.CommandInfo;
import com.motyldrogi.bot.predictions.entity.PredictionResponseEntity;
import com.motyldrogi.bot.predictions.entity.PredictionStatusEntity;
import com.motyldrogi.bot.predictions.entity.TopPredictorEntity;
import com.motyldrogi.bot.user.UserEntity;

public class PredictionCommand implements CommandExecutor {

    private TwitchApiService twitchApiService;
    private PredictionService predictionService;

    public PredictionCommand(TwitchApiService twitchApiService, PredictionService predictionService){
        this.twitchApiService = twitchApiService;
        this.predictionService = predictionService;
    }

    @CommandInfo(value = "prediction", minArguments = 0, maxArguments = 5, usage = "[action] [title] [duration (s)] [\"choices\"]", description = "Actions on prediction")
    @Override
    public void execute(CommandParser commandParser, UserEntity user) {
        String[] args = commandParser.getArgs();

        // !prediction - Get current prediction
        if (args.length == 0){
            PredictionResponseEntity currentPrediction = predictionService.getCurrentPrediction();
            if (currentPrediction == null){
                twitchApiService.sendMessage("No current prediction @" + user.getName());
                return;
            }
            twitchApiService.sendMessage("Current prediction : " + currentPrediction.getTitle());
            return;
        }
        // !prediction new title duration choice1 choice2
        if (args[0].equalsIgnoreCase("new")){
            PredictionResponseEntity currentPoll = predictionService.createPrediction(args[1], args[2], Arrays.copyOfRange(args, args.length-2, args.length));
            twitchApiService.sendAnnouncement("Vote to the current prediction : " + currentPoll.getTitle());
            return;
        }
        // !prediction resolve winningOutcome
        if (args[0].equalsIgnoreCase("resolve")){
            PredictionResponseEntity response = predictionService.resolveCurrentPrediction(args[1]);
            TopPredictorEntity bestPredictor = response.getBestPredictor(response.getWinningOutcomeId());
            twitchApiService.sendAnnouncement(String.format(
                "Prediction : %s is the correct answer ! Congrats to %s who just won %s points with %s points spent !",
                args[1], bestPredictor.getUserName(), bestPredictor.getChannelPointsWon(), bestPredictor.getChannelPointsUsed()));
            return;
        }
        // !prediction cancel
        if (args[0].equalsIgnoreCase("cancel")){
            PredictionResponseEntity response = predictionService.cancelCurrentPrediction();
            twitchApiService.sendAnnouncement("Prediction canceled, you have been refunded !");
            return;
        }

        if (args[0].equalsIgnoreCase("lock")){
            PredictionStatusEntity status = predictionService.getCurrentPrediction().getStatus();
            if (status.equals(PredictionStatusEntity.LOCKED)) {
                twitchApiService.sendMessage("Prediction already locked");
                return;
            }

            PredictionResponseEntity response = predictionService.lockCurrentPrediction();
            twitchApiService.sendAnnouncement("Prediction is lock ! Stay tuned for the answer");
            return;
        }

        twitchApiService.sendMessage("Invalid command @" + user.getName());
    }
    
}
