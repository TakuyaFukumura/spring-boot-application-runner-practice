package com.example.applicationrunner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * ApplicationContextの初期化完了後に、1回だけ引数処理を開始します。
 */
@Component
public class CommandLineArgumentsRunner implements ApplicationRunner {

    private final CommandLineArgumentsProcessor processor;

    public CommandLineArgumentsRunner(CommandLineArgumentsProcessor processor) {
        this.processor = processor;
    }

    @Override
    public void run(ApplicationArguments args) {
        processor.process(args);
    }
}
