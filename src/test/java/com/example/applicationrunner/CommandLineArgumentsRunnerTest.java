package com.example.applicationrunner;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(args = {"--name=Taro", "--verbose", "input.txt"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class CommandLineArgumentsRunnerTest {

    @MockitoBean
    private CommandLineArgumentsProcessor processor;

    @Autowired
    private List<ApplicationRunner> runners;

    @Test
    void runnerExecutesOnceAfterApplicationContextStarts() {
        verify(processor, times(1)).process(org.mockito.ArgumentMatchers.any());
    }

    @Test
    void runnersExecuteInDeclaredOrder() {
        assertEquals(List.of(StartupOrderRunner.class, CommandLineArgumentsRunner.class),
                runners.stream().map(Object::getClass).toList());
    }
}
