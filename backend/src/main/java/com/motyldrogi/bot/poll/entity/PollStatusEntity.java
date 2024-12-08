package com.motyldrogi.bot.poll.entity;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_VALUES)
public enum PollStatusEntity {

    ACTIVE("ACTIVE"),

    COMPLETED("COMPLETED"),

    TERMINATED("TERMINATED"),

    ARCHIVED("ARCHIVED"),

    MODERATED("MODERATED"),
    @JsonEnumDefaultValue
    INVALID("INVALID");

    private final String status;

    private PollStatusEntity(String status){
        this.status = status;
    }
    
    public String getStatus(){
        return this.status;
    }

}


