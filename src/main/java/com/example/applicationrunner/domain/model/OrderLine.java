package com.example.applicationrunner.domain.model;

import java.util.Objects;

public record OrderLine(ProductName product, Quantity quantity) {
    public OrderLine {
        Objects.requireNonNull(product, "商品名は必須です。");
        Objects.requireNonNull(quantity, "数量は必須です。");
    }
}
