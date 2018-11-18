package com.windsor.foodapp.enums;

import java.util.HashMap;
import java.util.Map;

public enum CLIENT_ROLE {

    USER(0),
    VENDOR(1);

    private int value;

    private static final Map<Integer, CLIENT_ROLE> MY_MAP = new HashMap<>();
    static {
        for (CLIENT_ROLE myEnum : values()) {
            MY_MAP.put(myEnum.getValue(), myEnum);
        }
    }

    CLIENT_ROLE(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static CLIENT_ROLE getByValue(int value) {
        return MY_MAP.get(value);
    }

}
