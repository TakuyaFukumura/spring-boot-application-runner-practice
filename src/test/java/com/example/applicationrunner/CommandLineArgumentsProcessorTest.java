package com.example.applicationrunner;

import org.junit.jupiter.api.Test;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.DefaultApplicationArguments;

import static org.junit.jupiter.api.Assertions.assertThrows;

class CommandLineArgumentsProcessorTest {

    private final CommandLineArgumentsProcessor processor = new CommandLineArgumentsProcessor();

    @Test
    void rejectsBlankName() {
        ApplicationArguments arguments = new DefaultApplicationArguments("--name=");

        assertThrows(IllegalArgumentException.class, () -> processor.process(arguments));
    }

    @Test
    void acceptsNameVerboseAndNonOptionArguments() {
        ApplicationArguments arguments = new DefaultApplicationArguments("--name=Taro", "--verbose", "input.txt");

        processor.process(arguments);
    }

    @Test
    void usesDefaultNameWhenNameIsNotSpecified() {
        ApplicationArguments arguments = new DefaultApplicationArguments();

        processor.process(arguments);
    }
}
