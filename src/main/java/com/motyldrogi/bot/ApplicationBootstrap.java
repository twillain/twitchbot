package com.motyldrogi.bot;

import com.motyldrogi.bot.client.TwitchWebSocketService;
import com.motyldrogi.bot.command.*;
import com.motyldrogi.bot.command.defaults.impl.CommandRegistry;
import com.motyldrogi.bot.repository.UserRepository;
import com.motyldrogi.bot.service.AutomatedMessageService;
import com.motyldrogi.bot.service.TwitchService;
import com.motyldrogi.bot.subscription.SubscriptionRegisterService;

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
    TwitchService twitchService,
    CommandRegistry commandRegistry,
    AutomatedMessageService automatedMessageService,
    TwitchWebSocketService twitchWebSocketService,
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
  private UserRepository userRepository;

  @Override
  public void run(String... args) throws Exception {
    subscriptionRegisterService.addImplementedSubscriptions(
      "channel.follow",
      //"channel.subscribe",
      "channel.chat.message"
    );
    twitchWebSocketService.connectWebSocket();
    automatedMessageService.initiate();
    
		// Register commands
    this.commandRegistry.registerByExecutors(
      new CounterCommand(userRepository),
      new DiceCommand(),
      new TestCommand(),
      new SetAdminCommand(userRepository),
      new NumberMessagesSentCommand(),
      new AutomatedMessagesCommand(automatedMessageService)
    );

  
  }
}
