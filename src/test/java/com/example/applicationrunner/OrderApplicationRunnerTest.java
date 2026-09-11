package com.example.applicationrunner;

import com.example.applicationrunner.presentation.OrderApplicationRunner;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(args = {"list"})
class OrderApplicationRunnerTest {
    @Autowired
    private List<ApplicationRunner> runners;

    @Test
    void runnersKeepStartupOrderAndOrderRunnerRuns() {
        assertEquals(List.of("StartupOrderRunner", "OrderApplicationRunner"),
                runners.stream().map(runner -> runner.getClass().getSimpleName()).toList());
    }
}
