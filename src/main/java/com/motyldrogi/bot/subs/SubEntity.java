package com.motyldrogi.bot.subs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.motyldrogi.bot.user.UserEntity;

@Entity
public class SubEntity {

    @Id @Column(nullable = false, unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(nullable = false)
    private String createdAt;

    @Column
    private String endedAt;

    @Column(nullable = false)
    private String tier;

    @Column(nullable = false)
    private boolean isGift;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setId(String id){
        this.id = Long.parseLong(id);
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getEndedAt() {
        return endedAt;
    }

    public void setEndedAt(String endedAt) {
        this.endedAt = endedAt;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public boolean isGift() {
        return isGift;
    }

    public void setIsGift(boolean isGift) {
        this.isGift = isGift;
    }
    
    
}