package com.example.applicationrunner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * {@code @Component} によってBean登録され、ApplicationContextの初期化完了後に
 * {@link #run(ApplicationArguments)} が1回だけ呼び出されます。
 *
 * {@code @Order} は複数のRunnerがある場合だけ意味を持ち、このRunnerを引数処理の段階として
 * 2番目に実行することを明示しています。
 */
@Component
@Order(2)
public class CommandLineArgumentsRunner implements ApplicationRunner {

    private final CommandLineArgumentsProcessor processor;

    public CommandLineArgumentsRunner(CommandLineArgumentsProcessor processor) {
        this.processor = processor;
    }

    @Override
    public void run(ApplicationArguments args) {
        // Spring Bootが解析済みのApplicationArgumentsを受け取り、処理の詳細はサービスへ委譲します。
        processor.process(args);
    }
}
