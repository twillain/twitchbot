package com.motyldrogi.bot;

import com.motyldrogi.bot.TwitchApi.TwitchApiService;
import com.motyldrogi.bot.TwitchWebSocket.TwitchWebSocketService;
import com.motyldrogi.bot.automatedMessages.AutomatedMessageService;
import com.motyldrogi.bot.automatedMessages.AutomatedMessagesCommand;
import com.motyldrogi.bot.command.*;
import com.motyldrogi.bot.command.defaults.impl.CommandRegistry;
import com.motyldrogi.bot.poll.PollService;
import com.motyldrogi.bot.poll.entity.PollCommand;
import com.motyldrogi.bot.predictions.PredictionCommand;
import com.motyldrogi.bot.predictions.PredictionService;
import com.motyldrogi.bot.role.SetAdminCommand;
import com.motyldrogi.bot.stream.entity.StreamService;
import com.motyldrogi.bot.subs.SubService;
import com.motyldrogi.bot.subscription.SubscriptionRegisterService;
import com.motyldrogi.bot.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication(exclude = {})
public class ApplicationBootstrap implements CommandLineRunner {

  private final CommandRegistry commandRegistry;
  private final AutomatedMessageService automatedMessageService;
  private final TwitchWebSocketService twitchWebSocketService;
  private final SubscriptionRegisterService subscriptionRegisterService;

  public ApplicationBootstrap(
    CommandRegistry commandRegistry,
    AutomatedMessageService automatedMessageService,
    TwitchWebSocketService twitchWebSocketService,
    SubService subService,
    SubscriptionRegisterService subscriptionRegisterService) {
    this.commandRegistry = commandRegistry;
    this.automatedMessageService = automatedMessageService;
    this.twitchWebSocketService = twitchWebSocketService;
    this.subscriptionRegisterService = subscriptionRegisterService;
  }

	public static void main(String[] args) {
		SpringApplication.run(ApplicationBootstrap.class, args);
  }

  @Autowired
  private UserService userService;

  @Autowired
  private TwitchApiService twitchApiService;

  @Autowired
  private PollService pollService;

  @Autowired
  private PredictionService predictionService;

  @Autowired
  private StreamService streamInfoService;

  @Override
  public void run(String... args) throws Exception {
    /*subscriptionRegisterService.addImplementedSubscriptions(
      "channel.follow",
      //"channel.subscribe",
      "channel.chat.message"
    );*/

    subscriptionRegisterService.addAllImplementedSubscriptions();
    twitchWebSocketService.connectWebSocket();
    automatedMessageService.initiate();
    
		// Register commands
    this.commandRegistry.registerByExecutors(
      new CounterCommand(twitchApiService, userService),
      new DiceCommand(twitchApiService),
      new TestCommand(twitchApiService, streamInfoService),
      new SetAdminCommand(twitchApiService, userService),
      new NumberMessagesSentCommand(twitchApiService),
      new AutomatedMessagesCommand(twitchApiService ,automatedMessageService),
      new PollCommand(twitchApiService, pollService),
      new PredictionCommand(twitchApiService, predictionService),
      new AnnouncementCommand(twitchApiService)
    );

  
  }
}
