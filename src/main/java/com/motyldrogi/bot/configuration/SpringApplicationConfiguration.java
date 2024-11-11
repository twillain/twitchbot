package com.motyldrogi.bot.configuration;


import com.motyldrogi.bot.command.defaults.impl.CommandRegistry;
import com.motyldrogi.bot.component.MessageComponent;
import com.motyldrogi.bot.repository.AutomatedMessageRepository;
import com.motyldrogi.bot.repository.UserRepository;
import com.motyldrogi.bot.service.AutomatedMessageService;
import com.motyldrogi.bot.service.TwitchService;
import com.motyldrogi.bot.service.impl.AutomatedMessageServiceImpl;
import com.motyldrogi.bot.service.impl.TwitchServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
  public CommandRegistry commandRegistryBean() {
    return new CommandRegistry(this.twitchService());
  }

  @Bean
  public AutomatedMessageService automatedMessageServiceBean(){
    return new AutomatedMessageServiceImpl(this.twitchService(), automatedMessageRepository);
  }
}
