package com.motyldrogi.bot.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.motyldrogi.bot.entity.impl.AutomatedMessageEntityImpl;

public interface AutomatedMessageRepository extends PagingAndSortingRepository<AutomatedMessageEntityImpl, Long> {
    
}
