package com.motyldrogi.bot.automatedMessages;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import com.motyldrogi.bot.TwitchApi.TwitchApiService;

public class AutomatedMessageService {

    private AutomatedMessageRepository automatedMessageRepository;

    private List<AutomatedMessageEntity> messages;

    @SuppressWarnings("rawtypes")
    private List<ScheduledFuture> scheduledTasks = null;

    private ScheduledExecutorService scheduler = null;

    private final TwitchApiService twitchApiService;

    public AutomatedMessageService(TwitchApiService twitchApiService, AutomatedMessageRepository automatedMessageRepository){
        this.twitchApiService = twitchApiService;
        this.automatedMessageRepository = automatedMessageRepository;

        scheduledTasks = new ArrayList<>();
    }
    
    public List<AutomatedMessageEntity> getAllAutomatedMessages(){
        return (List<AutomatedMessageEntity>) automatedMessageRepository.findAll();
    }

    public void removeAutomatedMessageById(AutomatedMessageEntity message){
        automatedMessageRepository.deleteById(message.getIdentifier());
        removeAutomatedMessage(message);
    }

    public void removeAutomatedMessageById(Long identifier){
        automatedMessageRepository.deleteById(identifier);
        for (int i=0 ; i < messages.size(); i++){
            if (messages.get(i).getIdentifier().equals(identifier)) removeAutomatedMessage(messages.get(i));
        };
    }

    public void removeAutomatedMessage(AutomatedMessageEntity message){
        int position = messages.indexOf(message);
        messages.remove(message);
        scheduledTasks.get(position).cancel(true);
        scheduledTasks.remove(position);
    }

    public void addAutomatedMessage(AutomatedMessageEntity message){
        automatedMessageRepository.save(message);
        scheduledTasks.add(scheduler.scheduleWithFixedDelay(() -> twitchApiService.sendMessage(message.getMessage()), message.getTimerDelay(), message.getTimerPeriod(), TimeUnit.MINUTES));
    }


    public void initiate(){

        messages = this.getAllAutomatedMessages();
        scheduler = Executors.newScheduledThreadPool(messages.size());
        messages.forEach(m -> {
            scheduledTasks.add(scheduler.scheduleWithFixedDelay(() -> twitchApiService.sendMessage(m.getMessage()), m.getTimerDelay(), m.getTimerPeriod(), TimeUnit.MINUTES));
        });
    }
}
