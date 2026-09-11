package com.example.applicationrunner.infrastructure.repository;

import com.example.applicationrunner.domain.model.Order;
import com.example.applicationrunner.domain.model.OrderId;
import com.example.applicationrunner.domain.repository.DuplicateOrderException;
import com.example.applicationrunner.domain.repository.OrderRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InMemoryOrderRepository implements OrderRepository {
    private final Map<OrderId, Order> orders = new LinkedHashMap<>();

    @Override
    public synchronized void save(Order order) {
        if (orders.containsKey(order.id())) {
            throw new DuplicateOrderException(order.id().value());
        }
        orders.put(order.id(), order);
    }

    @Override
    public synchronized List<Order> findAll() {
        return List.copyOf(new ArrayList<>(orders.values()));
    }

    @Override
    public synchronized boolean existsById(OrderId id) {
        return orders.containsKey(id);
    }
}
