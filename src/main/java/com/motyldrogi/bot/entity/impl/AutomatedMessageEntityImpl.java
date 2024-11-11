package com.motyldrogi.bot.entity.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.motyldrogi.bot.util.Buildable;

@Entity
@Table(name = "automated_message")
public class AutomatedMessageEntityImpl {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long identifier;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private Long timerDelay;

    @Column(nullable = false)
    private Long timerPeriod;

    private AutomatedMessageEntityImpl(){

    }

    private AutomatedMessageEntityImpl(Builder builder){
        this.identifier = builder.identifier;
        this.message = builder.message;
        this.timerDelay = builder.timerDelay;
        this.timerPeriod = builder.timerPeriod;
    }

    public Long getIdentifier(){
        return this.identifier;
    }

    public void setIdentifier(Long identifier){
        this.identifier = identifier;
    }

    public String getMessage(){
        return this.message;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public Long getTimerDelay(){
        return this.timerDelay;
    }

    public void setTimerDelay(Long timerDelay){
        this.timerDelay = timerDelay;
    }

    public Long getTimerPeriod(){
        return this.timerPeriod;
    }

    public void setTimerPeriod(Long timerPeriod){
        this.timerPeriod = timerPeriod;
    }

    public static class Builder implements Buildable<AutomatedMessageEntityImpl> {

        private Long identifier;
        private String message;
        private Long timerDelay;
        private Long timerPeriod;

        public Builder withIdentifier(Long identifier){
            this.identifier = identifier;
            return this;
        }

        public Builder withMessage(String message){
            this.message = message;
            return this;
        }

        public Builder withTimerDelay(Long timerDelay){
            this.timerDelay = timerDelay;
            return this;
        }

        public Builder withTimerPeriod(Long timerPeriod){
            this.timerPeriod = timerPeriod;
            return this;
        }

        @Override
        public AutomatedMessageEntityImpl build() {
            return new AutomatedMessageEntityImpl(this);
        }
    }
}
