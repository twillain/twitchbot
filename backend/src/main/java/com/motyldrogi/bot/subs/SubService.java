package com.motyldrogi.bot.subs;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.motyldrogi.bot.subs.subGift.SubGiftEntity;
import com.motyldrogi.bot.subs.subGift.SubGiftRepository;
import com.motyldrogi.bot.user.UserEntity;
import com.motyldrogi.bot.user.UserService;

@Service
public class SubService {

    private final UserService userService;
    private final SubRepository subRepository;
    private final SubGiftRepository subGiftRepository;

    public SubService(UserService userService, SubRepository subRepository, SubGiftRepository subGiftRepository) {
        this.userService = userService;
        this.subRepository = subRepository;
        this.subGiftRepository = subGiftRepository;
    }

    @Transactional
    public SubEntity addSub(UserEntity user, String subId ,String createdAt, String tier, boolean isGift) {

        if (user == null) {
            throw new IllegalArgumentException("No user found.");
        }

        SubEntity sub = new SubEntity();
        sub.setId(subId);
        sub.setUser(user);
        sub.setCreatedAt(createdAt);
        sub.setTier(tier);
        sub.setIsGift(isGift);

        user.setSub(sub);  // Mise à jour de l'état utilisateur
        userService.saveUser(user);  // Sauvegarde via UserService

        return subRepository.save(sub);
    }

    @Transactional
    public void endSub(UserEntity user, String endedAt){

        if (user == null) {
            throw new IllegalArgumentException("No user found.");
        }

        SubEntity sub = user.getSub();
        sub.setEndedAt(endedAt);
        subRepository.save(sub);

        user.setSub(null);
        userService.saveUser(user);
    }

    @Transactional
    public void addNotAnonymousSubGift(UserEntity user, String subGiftId, String createdAt, int total, String tier, int cumulativeTotal){
        addSubGift(user, subGiftId, createdAt, total, tier, false, cumulativeTotal);
    }

    @Transactional
    public void addAnonymousSubGift(UserEntity user, String subGiftId, String createdAt, int total){
        addSubGift(user, subGiftId, createdAt, total, createdAt, true, 0);
    }

    @Transactional
    private void addSubGift(UserEntity user, String subGiftId, String createdAt, int total, String tier, boolean isAnonymous,int cumulativeTotal) {

        if (user == null) {
            throw new IllegalArgumentException("No user found.");
        }

        SubGiftEntity subGift = new SubGiftEntity();
        subGift.setId(subGiftId);
        subGift.setUser(user);
        subGift.setCreatedAt(createdAt);
        subGift.setTotal(total);
        subGift.setTier(tier);
        subGift.setAnonymous(isAnonymous);
        if (!isAnonymous) {
            user.setTotalSubGifted(cumulativeTotal);
            userService.saveUser(user);
        }
        
        subGiftRepository.save(subGift);

    }
}

