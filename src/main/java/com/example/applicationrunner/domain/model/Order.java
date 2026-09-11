package com.example.applicationrunner.domain.model;

import java.util.Objects;

public final class Order {
    private final OrderId id;
    private final OrderLine line;

    private Order(OrderId id, OrderLine line) {
        this.id = Objects.requireNonNull(id, "注文IDは必須です。");
        this.line = Objects.requireNonNull(line, "注文明細は必須です。");
    }

    public static Order create(OrderId id, OrderLine line) {
        return new Order(id, line);
    }

    public OrderId id() {
        return id;
    }

    public OrderLine line() {
        return line;
    }
}
