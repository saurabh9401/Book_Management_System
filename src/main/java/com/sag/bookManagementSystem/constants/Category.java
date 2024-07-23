package com.sag.bookManagementSystem.constants;

public enum Category {

    LITERATURE(0),
    ACTION(1),
    DRAMA(2),
    TECHNOLOGY(3),
    POETRY(4),
    MEDIA(5),
    OTHERS(6);

    private final int value;

    private Category(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
