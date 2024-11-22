package com.motyldrogi.bot.user;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {

    Optional<UserEntity> findByName(String name);

    @NonNull Optional<UserEntity> findById(@NonNull Long id);
}
