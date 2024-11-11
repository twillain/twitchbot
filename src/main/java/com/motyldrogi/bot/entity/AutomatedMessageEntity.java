package com.motyldrogi.bot.entity;

public interface AutomatedMessageEntity extends Entity<Long>{
    
    void setMessage(String message);

    void setTimerDelay(long delay);

    void setTimerPeriod(long period);

    String getMessage();

    long getTimerDelay();

    long getTimerPeriod();
}
