package com.example.applicationrunner.domain.repository;

import com.example.applicationrunner.domain.model.Order;
import com.example.applicationrunner.domain.model.OrderId;

import java.util.List;

public interface OrderRepository {
    void save(Order order);
    List<Order> findAll();
    boolean existsById(OrderId id);
}
