package com.example.applicationrunner.domain;

import com.example.applicationrunner.domain.model.Order;
import com.example.applicationrunner.domain.model.OrderId;
import com.example.applicationrunner.domain.model.OrderLine;
import com.example.applicationrunner.domain.model.ProductName;
import com.example.applicationrunner.domain.model.Quantity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderDomainTest {
    @Test
    void createsValidOrder() {
        Order order = Order.create(new OrderId("1001"), new OrderLine(new ProductName("Book"), new Quantity(2)));
        assertEquals("1001", order.id().value());
        assertEquals(2, order.line().quantity().value());
    }

    @Test
    void rejectsInvalidValues() {
        assertThrows(IllegalArgumentException.class, () -> new OrderId(" "));
        assertThrows(IllegalArgumentException.class, () -> new ProductName(""));
        assertThrows(IllegalArgumentException.class, () -> new Quantity(0));
    }

    @Test
    void rejectsMissingOrderParts() {
        assertThrows(NullPointerException.class, () -> Order.create(null,
                new OrderLine(new ProductName("Book"), new Quantity(1))));
        assertThrows(NullPointerException.class, () -> Order.create(new OrderId("1001"), null));
        assertThrows(NullPointerException.class, () -> new OrderLine(null, new Quantity(1)));
        assertThrows(NullPointerException.class, () -> new OrderLine(new ProductName("Book"), null));
    }
}
