package com.windsor.foodapp.enums;

public enum CLIENT_STATUS_ENUM {
    ACTIVE(0),
    BLOCKED(1);

    private int value;
    CLIENT_STATUS_ENUM(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}