package com.example.applicationrunner.domain.repository;

public class DuplicateOrderException extends RuntimeException {
    public DuplicateOrderException(String orderId) {
        super("注文IDは既に登録されています: " + orderId);
    }
}
