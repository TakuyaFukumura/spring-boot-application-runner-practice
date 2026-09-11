package com.example.applicationrunner.presentation;

import com.example.applicationrunner.application.dto.ListOrdersCommand;
import com.example.applicationrunner.application.dto.OrderCommand;
import com.example.applicationrunner.application.dto.RegisterOrderCommand;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CliCommandParser {
    public OrderCommand parse(ApplicationArguments arguments) {
        List<String> commands = arguments.getNonOptionArgs();
        if (commands.size() != 1) {
            throw new IllegalArgumentException("サブコマンド register または list を1つ指定してください。");
        }
        return switch (commands.getFirst()) {
            case "register" -> new RegisterOrderCommand(required(arguments, "order-id"),
                    required(arguments, "product"), requiredQuantity(arguments));
            case "list" -> new ListOrdersCommand();
            default -> throw new IllegalArgumentException("未対応のサブコマンドです: " + commands.getFirst());
        };
    }

    private String required(ApplicationArguments arguments, String name) {
        List<String> values = arguments.getOptionValues(name);
        if (values == null || values.size() != 1 || values.getFirst().isBlank()) {
            throw new IllegalArgumentException("--" + name + "には空でない値を1つ指定してください。");
        }
        return values.getFirst();
    }

    private int requiredQuantity(ApplicationArguments arguments) {
        String value = required(arguments, "quantity");
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("--quantityには整数を指定してください。", exception);
        }
    }
}
