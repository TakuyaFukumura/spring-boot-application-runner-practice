package com.example.applicationrunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest(args = {"--name=Taro", "--verbose", "input.txt"})
class CommandLineArgumentsRunnerTest {

    @MockitoBean
    private CommandLineArgumentsProcessor processor;

    @Test
    void runnerExecutesOnceAfterApplicationContextStarts() {
        verify(processor, times(1)).process(org.mockito.ArgumentMatchers.any());
    }
}
