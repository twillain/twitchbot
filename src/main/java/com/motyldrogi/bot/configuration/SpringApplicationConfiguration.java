package com.motyldrogi.bot.configuration;


import com.motyldrogi.bot.client.TwitchWebSocketService;
import com.motyldrogi.bot.command.defaults.impl.CommandRegistry;
import com.motyldrogi.bot.component.MessageComponent;
import com.motyldrogi.bot.component.NotificationHandler;
import com.motyldrogi.bot.notification.FollowNotificationHandler;
import com.motyldrogi.bot.notification.MessageNotificationHandler;
import com.motyldrogi.bot.notification.SubscribeNotificationHandler;
import com.motyldrogi.bot.repository.AutomatedMessageRepository;
import com.motyldrogi.bot.repository.UserRepository;
import com.motyldrogi.bot.service.AutomatedMessageService;
import com.motyldrogi.bot.service.CommandProcessorService;
import com.motyldrogi.bot.service.SessionService;
import com.motyldrogi.bot.service.TwitchApiService;
import com.motyldrogi.bot.service.TwitchService;
import com.motyldrogi.bot.service.impl.AutomatedMessageServiceImpl;
import com.motyldrogi.bot.service.impl.TwitchServiceImpl;
import com.motyldrogi.bot.subscription.SubscriptionRegisterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SpringApplicationConfiguration {

  private final MessageComponent messageComponent;
  private final AppProperties properties;

  @Autowired
  public UserRepository userRepository;

  @Autowired
  public AutomatedMessageRepository automatedMessageRepository;

  public SpringApplicationConfiguration(MessageComponent messageComponent, AppProperties properties) {
    this.messageComponent = messageComponent;
    this.properties = properties;
  }

  @Bean
  public TwitchService twitchService() {
    return new TwitchServiceImpl(this.messageComponent, this.properties, userRepository);
  }

  @Bean
  public CommandRegistry commandRegistryBean(CommandProcessorService commandProcessorService) {
    return new CommandRegistry(commandProcessorService);
  }

  @Bean
  public AutomatedMessageService automatedMessageServiceBean(TwitchApiService twitchApiService){
    return new AutomatedMessageServiceImpl(twitchApiService, automatedMessageRepository);
  }
  
  @Bean
  public TwitchWebSocketService twitchWebSocketService(SessionService sessionService, SubscriptionRegisterService subscriptionRegisterService, NotificationHandler notificationHandler) {
    return new TwitchWebSocketService(sessionService, subscriptionRegisterService, notificationHandler);
  }

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @Bean
  public MessageNotificationHandler messageNotificationHandler(CommandProcessorService commandProcessorService) {
    return new MessageNotificationHandler(commandProcessorService, this.properties, userRepository);
  }

  @Bean
  public FollowNotificationHandler followNotificationHandler(TwitchApiService twitchApiService) {
    return new FollowNotificationHandler(twitchApiService, userRepository);
  }

  @Bean
  public SubscribeNotificationHandler subscribeNotificationHandler() {
    return new SubscribeNotificationHandler();
  }

}
