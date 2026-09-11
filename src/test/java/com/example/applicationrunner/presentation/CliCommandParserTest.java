package com.example.applicationrunner.presentation;

import com.example.applicationrunner.application.dto.ListOrdersCommand;
import com.example.applicationrunner.application.dto.RegisterOrderCommand;
import org.junit.jupiter.api.Test;
import org.springframework.boot.DefaultApplicationArguments;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CliCommandParserTest {
    private final CliCommandParser parser = new CliCommandParser();

    @Test
    void parsesRegisterCommand() {
        var command = parser.parse(new DefaultApplicationArguments(
                "register", "--order-id=1001", "--product=Book", "--quantity=2"));

        assertEquals(new RegisterOrderCommand("1001", "Book", 2), command);
    }

    @Test
    void parsesListCommand() {
        assertEquals(new ListOrdersCommand(),
                parser.parse(new DefaultApplicationArguments("list")));
    }

    @Test
    void rejectsMissingOrInvalidCommandArguments() {
        assertThrows(IllegalArgumentException.class,
                () -> parser.parse(new DefaultApplicationArguments("register")));
        assertThrows(IllegalArgumentException.class,
                () -> parser.parse(new DefaultApplicationArguments(
                        "register", "--order-id=1001", "--product=Book", "--quantity=zero")));
        assertThrows(IllegalArgumentException.class,
                () -> parser.parse(new DefaultApplicationArguments("unknown")));
    }
}
