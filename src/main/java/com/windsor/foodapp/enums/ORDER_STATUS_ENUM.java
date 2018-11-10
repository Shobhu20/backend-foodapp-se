package com.windsor.foodapp.enums;

import java.util.HashMap;
import java.util.Map;

public enum ORDER_STATUS_ENUM {
    ACTIVE(0),
    COMPLETED(1);

    private int value;

    private static final Map<Integer, ORDER_STATUS_ENUM> MY_MAP = new HashMap<>();
    static {
        for (ORDER_STATUS_ENUM myEnum : values()) {
            MY_MAP.put(myEnum.getValue(), myEnum);
        }
    }

    ORDER_STATUS_ENUM(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ORDER_STATUS_ENUM getByValue(int value) {
        return MY_MAP.get(value);
    }

}
