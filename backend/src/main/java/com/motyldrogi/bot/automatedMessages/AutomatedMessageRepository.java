package com.motyldrogi.bot.automatedMessages;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutomatedMessageRepository extends PagingAndSortingRepository<AutomatedMessageEntity, Long> {
    
}
