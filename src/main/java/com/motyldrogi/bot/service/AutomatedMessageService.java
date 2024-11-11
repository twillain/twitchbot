package com.motyldrogi.bot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.motyldrogi.bot.entity.impl.AutomatedMessageEntityImpl;

@Service
public interface AutomatedMessageService {
    
    List<AutomatedMessageEntityImpl> getAllAutomatedMessages();

    void removeAutomatedMessage(AutomatedMessageEntityImpl message);

    void addAutomatedMessage(AutomatedMessageEntityImpl message);

    void removeAutomatedMessageById(Long identifier);

    void initiate();
}
