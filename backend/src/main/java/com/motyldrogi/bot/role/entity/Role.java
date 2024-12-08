package com.motyldrogi.bot.role.entity;

public enum Role {
  
    VIEWER(0),
    MODERATOR(1),
    ADMIN(2);

    private final int level;

    Role(int level){
        this.level = level;
    }

    public int getLevel(){
        return this.level;
    }

}
