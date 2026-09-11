package com.example.applicationrunner.application;

import com.example.applicationrunner.application.dto.ListOrdersCommand;
import com.example.applicationrunner.application.dto.OrderCommand;
import com.example.applicationrunner.application.dto.OrderResult;
import com.example.applicationrunner.application.dto.RegisterOrderCommand;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderCommandService {
    private final OrderApplicationService orders;

    public OrderCommandService(OrderApplicationService orders) {
        this.orders = orders;
    }

    public CommandResult execute(OrderCommand command) {
        if (command instanceof RegisterOrderCommand register) {
            return CommandResult.registered(orders.register(register));
        }
        if (command instanceof ListOrdersCommand) {
            return CommandResult.listed(orders.list());
        }
        throw new IllegalArgumentException("未対応のコマンドです。");
    }

    public record CommandResult(OrderResult registered, List<OrderResult> listed) {
        static CommandResult registered(OrderResult result) {
            return new CommandResult(result, List.of());
        }

        static CommandResult listed(List<OrderResult> results) {
            return new CommandResult(null, results);
        }
    }
}
