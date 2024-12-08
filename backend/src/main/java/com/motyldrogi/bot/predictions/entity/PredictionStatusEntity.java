package com.motyldrogi.bot.predictions.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_VALUES)
public enum PredictionStatusEntity {
    
    ACTIVE("ACTIVE"),
    CANCELED("CANCELED"),
    LOCKED("LOCKED"),
    RESOLVED("RESOLVED");

    private final String status;

    private PredictionStatusEntity(String status){
        this.status = status;
    }

    public String getStatus(){
        return this.status;
    }
}
