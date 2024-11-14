package com.motyldrogi.bot.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:app.properties")
@ConfigurationProperties
public class AppProperties {

  private String prefix;

  private String bearerToken;

  private String nickname;

  private String channel;

  private String clientId;

  private String oauth;

  private String userId;

  private String broadcasterUserId;

  public String getBroadcasterUserId() {
    return broadcasterUserId;
  }

  public void setBroadcasterUserId(String broadcasterUserId) {
    this.broadcasterUserId = broadcasterUserId;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getOauthToken() {
    return oauth;
  }

  public void setOauthToken(String oauth) {
    this.oauth = oauth;
  }

  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public String getPrefix() {
    return prefix;
  }

  public String getBearerToken() {
    return bearerToken;
  }

  public void setBearerToken(String bearerToken) {
      this.bearerToken = bearerToken;
  }

  public String getNickname() {
      return nickname;
  }

  public void setNickname(String nickname) {
      this.nickname = nickname;
  }

  public String getChannel() {
      return channel;
  }

  public void setChannel(String channel) {
      this.channel = channel;
  }

  public void setPrefix(String prefix) {
    this.prefix = prefix;
  }

}