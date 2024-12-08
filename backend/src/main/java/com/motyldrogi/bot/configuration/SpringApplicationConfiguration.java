package com.motyldrogi.bot.configuration;


import com.motyldrogi.bot.FrontWebSocket.BroadcastService;
import com.motyldrogi.bot.TwitchApi.TwitchApiService;
import com.motyldrogi.bot.TwitchWebSocket.TwitchWebSocketService;
import com.motyldrogi.bot.TwitchWebSocket.TwitchWebSocketSessionService;
import com.motyldrogi.bot.automatedMessages.AutomatedMessageRepository;
import com.motyldrogi.bot.automatedMessages.AutomatedMessageService;
import com.motyldrogi.bot.command.defaults.impl.CommandRegistry;
import com.motyldrogi.bot.component.NotificationHandler;
import com.motyldrogi.bot.notification.FollowNotificationHandler;
import com.motyldrogi.bot.notification.MessageNotificationHandler;
import com.motyldrogi.bot.notification.SubscribeNotificationHandler;
import com.motyldrogi.bot.service.CommandProcessorService;
import com.motyldrogi.bot.subs.SubRepository;
import com.motyldrogi.bot.subs.SubService;
import com.motyldrogi.bot.subs.subGift.SubGiftRepository;
import com.motyldrogi.bot.subscription.SubscriptionRegisterService;
import com.motyldrogi.bot.user.UserRepository;
import com.motyldrogi.bot.user.UserService;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SpringApplicationConfiguration {

  private final AppProperties properties;

  @Autowired
  public UserRepository userRepository;

  @Autowired
  public AutomatedMessageRepository automatedMessageRepository;

  @Autowired
  public SubRepository subRepository;

  @Autowired
  public SubGiftRepository subGiftRepository;

  @Autowired
  public BroadcastService broadcastService;

  public SpringApplicationConfiguration(AppProperties properties) {
    this.properties = properties;
  }

  @Bean
  public CommandRegistry commandRegistryBean(CommandProcessorService commandProcessorService) {
    return new CommandRegistry(commandProcessorService);
  }

  @Bean
  public AutomatedMessageService automatedMessageServiceBean(TwitchApiService twitchApiService){
    return new AutomatedMessageService(twitchApiService, automatedMessageRepository);
  }
  
  @Bean
  public TwitchWebSocketService twitchWebSocketService(TwitchWebSocketSessionService sessionService, SubscriptionRegisterService subscriptionRegisterService, NotificationHandler notificationHandler) {
    return new TwitchWebSocketService(sessionService, subscriptionRegisterService, notificationHandler);
  }

  @Bean
  public RestTemplate restTemplate() {
    RestTemplate restTemplate = new RestTemplate();
    CloseableHttpClient httpClient = HttpClientBuilder.create().build();
    HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
    restTemplate.setRequestFactory(requestFactory);
    return restTemplate;
  }

  @Bean
  public MessageNotificationHandler messageNotificationHandler(CommandProcessorService commandProcessorService, BroadcastService broadcastService, UserService userService) {
    return new MessageNotificationHandler(commandProcessorService, broadcastService,userService ,this.properties);
  }

  @Bean
  public FollowNotificationHandler followNotificationHandler(TwitchApiService twitchApiService, UserService userService) {
    return new FollowNotificationHandler(twitchApiService, userService);
  }

  @Bean
  public SubscribeNotificationHandler subscribeNotificationHandler(TwitchApiService twitchApiService, UserService userService, SubService subService) {
    return new SubscribeNotificationHandler(twitchApiService, userService, subService);
  }

}
