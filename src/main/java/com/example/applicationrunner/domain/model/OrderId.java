package com.example.applicationrunner.domain.model;

public record OrderId(String value) {
    public OrderId {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("注文IDは空にできません。");
        }
        value = value.trim();
    }
}
