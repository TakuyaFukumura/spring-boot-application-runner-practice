package com.example.applicationrunner.presentation;

import com.example.applicationrunner.application.OrderCommandService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class OrderApplicationRunner implements ApplicationRunner {
    private final CliCommandParser parser;
    private final OrderCommandService service;
    private final OrderOutput output;

    public OrderApplicationRunner(CliCommandParser parser, OrderCommandService service, OrderOutput output) {
        this.parser = parser;
        this.service = service;
        this.output = output;
    }

    @Override
    public void run(ApplicationArguments args) {
        output.print(service.execute(parser.parse(args)));
    }
}
