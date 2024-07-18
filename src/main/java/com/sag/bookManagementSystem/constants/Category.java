package com.sag.bookManagementSystem.constants;

public enum Category {

    literature(0),
    action(1),
    drama(2),
    technology(3),
    poetry(4),
    media(5),
    others(6);

    private final int value;

    private Category(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
