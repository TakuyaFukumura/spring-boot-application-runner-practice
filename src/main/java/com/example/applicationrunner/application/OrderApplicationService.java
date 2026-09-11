package com.example.applicationrunner.application;

import com.example.applicationrunner.application.dto.OrderResult;
import com.example.applicationrunner.application.dto.RegisterOrderCommand;
import com.example.applicationrunner.domain.model.Order;
import com.example.applicationrunner.domain.model.OrderId;
import com.example.applicationrunner.domain.model.OrderLine;
import com.example.applicationrunner.domain.model.ProductName;
import com.example.applicationrunner.domain.model.Quantity;
import com.example.applicationrunner.domain.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderApplicationService {
    private final OrderRepository repository;

    public OrderApplicationService(OrderRepository repository) {
        this.repository = repository;
    }

    public OrderResult register(RegisterOrderCommand command) {
        Order order = Order.create(new OrderId(command.orderId()),
                new OrderLine(new ProductName(command.product()), new Quantity(command.quantity())));
        repository.save(order);
        return toResult(order);
    }

    public List<OrderResult> list() {
        return repository.findAll().stream().map(this::toResult).toList();
    }

    private OrderResult toResult(Order order) {
        return new OrderResult(order.id().value(), order.line().product().value(), order.line().quantity().value());
    }
}
