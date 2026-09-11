package com.example.applicationrunner.domain.model;

public record ProductName(String value) {
    public ProductName {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("商品名は空にできません。");
        }
        value = value.trim();
    }
}
