package com.motyldrogi.bot.entity;

import com.motyldrogi.bot.util.Role;

public interface UserEntity extends Entity<Long> {
    
    String getName();
    void setName(String name);

    Role getRole();
    void setRole(Role role);

    Integer getNumberMessagesSent();
    void setNumberMessagesSent(Integer numberMessagesSent);

    Integer getCounterValue();
    void setCounterValue(Integer counter);


}
