package com.motyldrogi.bot.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import com.motyldrogi.bot.entity.impl.AutomatedMessageEntityImpl;
import com.motyldrogi.bot.repository.AutomatedMessageRepository;
import com.motyldrogi.bot.service.AutomatedMessageService;
import com.motyldrogi.bot.service.TwitchService;

public class AutomatedMessageServiceImpl implements AutomatedMessageService {

    private AutomatedMessageRepository automatedMessageRepository;

    private List<AutomatedMessageEntityImpl> messages;

    @SuppressWarnings("rawtypes")
    private List<ScheduledFuture> scheduledTasks = null;

    private ScheduledExecutorService scheduler = null;

    private TwitchService twitchService;

    public AutomatedMessageServiceImpl(TwitchService twitchService, AutomatedMessageRepository automatedMessageRepository){
        this.twitchService = twitchService;
        this.automatedMessageRepository = automatedMessageRepository;

        scheduledTasks = new ArrayList<>();
    }
    
    public List<AutomatedMessageEntityImpl> getAllAutomatedMessages(){
        return (List<AutomatedMessageEntityImpl>) automatedMessageRepository.findAll();
    }

    public void removeAutomatedMessageById(AutomatedMessageEntityImpl message){
        automatedMessageRepository.deleteById(message.getIdentifier());
        removeAutomatedMessage(message);
    }

    public void removeAutomatedMessageById(Long identifier){
        automatedMessageRepository.deleteById(identifier);
        for (int i=0 ; i < messages.size(); i++){
            if (messages.get(i).getIdentifier().equals(identifier)) removeAutomatedMessage(messages.get(i));
        };
    }

    public void removeAutomatedMessage(AutomatedMessageEntityImpl message){
        int position = messages.indexOf(message);
        messages.remove(message);
        scheduledTasks.get(position).cancel(true);
        scheduledTasks.remove(position);
    }

    public void addAutomatedMessage(AutomatedMessageEntityImpl message){
        automatedMessageRepository.save(message);
        scheduledTasks.add(scheduler.scheduleWithFixedDelay(() -> twitchService.sendMessage(message.getMessage()), message.getTimerDelay(), message.getTimerPeriod(), TimeUnit.MINUTES));
    }


    public void initiate(){

        messages = this.getAllAutomatedMessages();
        scheduler = Executors.newScheduledThreadPool(messages.size());
        messages.forEach(m -> {
            scheduledTasks.add(scheduler.scheduleWithFixedDelay(() -> twitchService.sendMessage(m.getMessage()), m.getTimerDelay(), m.getTimerPeriod(), TimeUnit.MINUTES));
        });
    }
}
