package com.example.applicationrunner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 複数のRunnerを使う場合の順序制御を示す、最初に実行されるRunnerです。
 */
@Component
@Order(1)
public class StartupOrderRunner implements ApplicationRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(StartupOrderRunner.class);

    @Override
    public void run(ApplicationArguments args) {
        LOGGER.info("startup order step=1");
    }
}
