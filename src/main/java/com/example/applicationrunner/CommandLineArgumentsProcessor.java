package com.example.applicationrunner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ApplicationRunnerから受け取った引数を処理します。
 */
@Service
public class CommandLineArgumentsProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandLineArgumentsProcessor.class);

    public void process(ApplicationArguments arguments) {
        String name = "World";
        // containsOptionは、--nameのようなオプションが指定されたかだけを判定します。
        if (arguments.containsOption("name")) {
            // getOptionValuesは、--name=Taroの値をリストとして取得します。
            List<String> values = arguments.getOptionValues("name");
            if (values == null || values.size() != 1 || values.getFirst().isBlank()) {
                throw new IllegalArgumentException("--nameには空でない値を1つ指定してください。");
            }
            name = values.getFirst();
        }

        boolean verbose = arguments.containsOption("verbose");
        // getNonOptionArgsは、input.txtのような--で始まらない引数を取得します。
        LOGGER.info("name={}, verbose={}, nonOptionArgs={}", name, verbose, arguments.getNonOptionArgs());
    }
}
