package com.yana.stepanova.model;


import java.util.Objects;

public enum Currency {
    UAH("UAH"),
    USD("USD"),
    EUR("EUR"),
    GBP("GBP");

    private final String code;

    Currency(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static Currency getByType(String type) {
        for (Currency item : Currency.values()) {
            if (Objects.equals(item.getCode(), type)) {
                return item;
            }
        }
        return null;
    }
}
