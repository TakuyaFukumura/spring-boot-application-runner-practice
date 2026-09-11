package com.example.applicationrunner.domain.model;

public record Quantity(int value) {
    public Quantity {
        if (value < 1) {
            throw new IllegalArgumentException("数量は1以上でなければなりません。");
        }
    }
}
