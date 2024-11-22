package com.motyldrogi.bot.subs.subGift;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubGiftRepository extends JpaRepository<SubGiftEntity, Long> {
    
}
