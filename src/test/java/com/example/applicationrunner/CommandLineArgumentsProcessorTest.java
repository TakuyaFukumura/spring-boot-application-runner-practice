package com.example.applicationrunner;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.DefaultApplicationArguments;

class CommandLineArgumentsProcessorTest {

    private final CommandLineArgumentsProcessor processor = new CommandLineArgumentsProcessor();

    @Test
    void rejectsBlankName() {
        ApplicationArguments arguments = new DefaultApplicationArguments("--name=");

        assertThrows(IllegalArgumentException.class, () -> processor.process(arguments));
    }
}
