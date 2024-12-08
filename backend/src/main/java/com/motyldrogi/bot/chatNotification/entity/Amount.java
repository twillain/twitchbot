package com.motyldrogi.bot.chatNotification.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Amount {
    @JsonProperty("value")
    private int value;

    @JsonProperty("decimal_place")
    private int decimalPlace;

    @JsonProperty("currency")
    private String currency;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getDecimalPlace() {
        return decimalPlace;
    }

    public void setDecimalPlace(int decimalPlace) {
        this.decimalPlace = decimalPlace;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    
}

