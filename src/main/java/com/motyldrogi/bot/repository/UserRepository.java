package com.motyldrogi.bot.repository;

import com.motyldrogi.bot.entity.impl.UserEntityImpl;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<UserEntityImpl, Long> {

    Optional<UserEntityImpl> findByName(String name);
}
