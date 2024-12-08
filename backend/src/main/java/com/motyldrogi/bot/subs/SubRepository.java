package com.motyldrogi.bot.subs;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SubRepository extends JpaRepository<SubEntity, Long> {
    
    @Query("SELECT s FROM SubEntity s WHERE s.user.id = :userId ORDER BY s.createdAt DESC")
    Optional<SubEntity> findLatestUserById(Long userId);
}
