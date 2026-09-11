package com.example.applicationrunner.presentation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class StartupOrderRunner implements ApplicationRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(StartupOrderRunner.class);

    @Override
    public void run(ApplicationArguments args) {
        LOGGER.info("startup order step=1");
    }
}
