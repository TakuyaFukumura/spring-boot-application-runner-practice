package com.example.applicationrunner.application;

import com.example.applicationrunner.application.dto.RegisterOrderCommand;
import com.example.applicationrunner.domain.model.Order;
import com.example.applicationrunner.domain.repository.OrderRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderApplicationServiceTest {
    private final StubRepository repository = new StubRepository();
    private final OrderApplicationService service = new OrderApplicationService(repository);

    @Test
    void registersAndListsOrder() {
        service.register(new RegisterOrderCommand("1", "Book", 2));
        assertEquals(List.of("1"), service.list().stream().map(result -> result.orderId()).toList());
    }

    @Test
    void rejectsDuplicateOrderId() {
        service.register(new RegisterOrderCommand("1", "Book", 1));
        assertThrows(com.example.applicationrunner.domain.repository.DuplicateOrderException.class,
                () -> service.register(new RegisterOrderCommand("1", "Pen", 1)));
    }

    @Test
    void rejectsInvalidOrderInput() {
        assertThrows(IllegalArgumentException.class,
                () -> service.register(new RegisterOrderCommand("", "Book", 1)));
        assertThrows(IllegalArgumentException.class,
                () -> service.register(new RegisterOrderCommand("2", "Book", 0)));
    }

    private static final class StubRepository implements OrderRepository {
        private final List<Order> orders = new ArrayList<>();

        public void save(Order order) {
            if (existsById(order.id())) throw new com.example.applicationrunner.domain.repository.DuplicateOrderException(order.id().value());
            orders.add(order);
        }

        public List<Order> findAll() {
            return List.copyOf(orders);
        }

        public boolean existsById(com.example.applicationrunner.domain.model.OrderId id) {
            return orders.stream().anyMatch(order -> order.id().equals(id));
        }
    }
}
