package com.windsor.foodapp.enums;

import java.util.HashMap;
import java.util.Map;

public enum CLIENT_STATUS_ENUM {
    ACTIVE(0),
    BLOCKED(1);

    private int value;

    private static final Map<Integer, CLIENT_STATUS_ENUM> MY_MAP = new HashMap<>();
    static {
        for (CLIENT_STATUS_ENUM myEnum : values()) {
            MY_MAP.put(myEnum.getValue(), myEnum);
        }
    }

    CLIENT_STATUS_ENUM(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
    public static CLIENT_STATUS_ENUM getByValue(int value) {
        return MY_MAP.get(value);
    }

}