package com.motyldrogi.bot.entity.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.motyldrogi.bot.entity.UserEntity;
import com.motyldrogi.bot.util.Role;
import com.motyldrogi.bot.util.Buildable;

@Entity
@Table(name = "user")
public class UserEntityImpl implements UserEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long identifier;

    @Column
    private String twitchId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private Integer numberMessagesSent;

    @Column(nullable = false)
    private Integer counter;

    @Column
    private String chatColor;

    @Column
    private String followedAt;

    @Column
    private String subscribedAt;

    private UserEntityImpl(){

    }

    private UserEntityImpl(Builder builder){
        this.identifier = builder.identifier;
        this.twitchId = builder.twitchId;
        this.name = builder.name;
        this.role = builder.role;
        this.numberMessagesSent = builder.numberMessagesSent;
        this.counter = builder.counter;
        this.chatColor = builder.chatColor;
        this.followedAt = builder.followedAt;
        this.subscribedAt = builder.subscribedAt;
    }

    public Long getIdentifier(){
        return this.identifier;
    }

    public void setIdentifier(Long identifier){
        this.identifier = identifier;
    }

    public String getTwitchId() {
        return twitchId;
    }

    public void setTwitchId(String twitchId) {
        this.twitchId = twitchId;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Role getRole(){
        return this.role;
    }

    public void setRole(Role role){
        this.role = role;
    }

    public Integer getNumberMessagesSent(){
        return this.numberMessagesSent;
    }

    public void setNumberMessagesSent(Integer numberMessagesSent){
        this.numberMessagesSent = numberMessagesSent;
    }

    public Integer getCounterValue(){
        return this.counter;
    }

    public void setCounterValue(Integer counter){
        this.counter = counter;
    }

    public String getChatColor(){
        return this.chatColor;
    }

    public void setChatColor(String chatColor){
        this.chatColor = chatColor;
    }

    public String getFollowedAt(){
        return this.followedAt;
    }

    public void setFollowedAt(String followedAt){
        this.followedAt = followedAt;
    }

    public String getSubscribedAt(){
        return this.subscribedAt;
    }

    public void setSubscribedAt(String subscribedAt){
        this.subscribedAt = subscribedAt;
    }


    public static class Builder implements Buildable<UserEntityImpl> {

        private Long identifier;
        private String twitchId;
        private String name;
        private Role role;
        private Integer numberMessagesSent;
        private Integer counter;
        private String chatColor;
        private String followedAt;
        private String subscribedAt;

        public Builder withName(String name){
            this.name = name;
            return this;
        }

        public Builder withIdentifier(Long identifier){
            this.identifier = identifier;
            return this;
        }

        public Builder withTwitchId(String twitchId){
            this.twitchId = twitchId;
            return this;
        }

        public Builder withRole(Role role){
            this.role = role;
            return this;
        }

        public Builder withNumberMessagesSent(Integer numberOfMessengesSent){
            this.numberMessagesSent = numberOfMessengesSent;
            return this;
        }

        public Builder withCounter(Integer counter){
            this.counter = counter;
            return this;
        }

        public Builder withChatColor(String chatColor){
            this.chatColor = chatColor;
            return this;
        }

        public Builder withFollowedAt(String followedAt){
            this.followedAt = followedAt;
            return this;
        }

        public Builder withSubscribedAt(String subscribedAt){
            this.subscribedAt = subscribedAt;
            return this;
        }

        @Override
        public UserEntityImpl build() {
            return new UserEntityImpl(this);
        }

        public Integer getCounter() {
            return counter;
        }
    
        public void setCounter(Integer counter) {
            this.counter = counter;
        }

    }
    
}
