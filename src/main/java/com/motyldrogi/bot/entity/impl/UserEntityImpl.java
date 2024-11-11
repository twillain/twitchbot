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

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private Integer numberMessagesSent;

    @Column(nullable = false)
    private Integer counter;

    private UserEntityImpl(){

    }

    private UserEntityImpl(Builder builder){
        this.identifier = builder.identifier;
        this.name = builder.name;
        this.role = builder.role;
        this.numberMessagesSent = builder.numberMessagesSent;
        this.counter = builder.counter;
    }

    public Long getIdentifier(){
        return this.identifier;
    }

    public void setIdentifier(Long identifier){
        this.identifier = identifier;
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

    

    public static class Builder implements Buildable<UserEntityImpl> {

        private Long identifier;
        private String name;
        private Role role;
        private Integer numberMessagesSent;
        private Integer counter;

        public Builder withName(String name){
            this.name = name;
            return this;
        }

        public Builder withIdentifier(Long identifier){
            this.identifier = identifier;
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

        @Override
        public UserEntityImpl build() {
            return new UserEntityImpl(this);
        }

    }
    
}
