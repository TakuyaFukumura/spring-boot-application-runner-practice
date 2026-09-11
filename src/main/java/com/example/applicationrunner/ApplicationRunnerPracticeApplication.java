package com.example.applicationrunner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * {@link SpringBootApplication} はコンポーネント探索と自動設定をまとめて有効にするため、
 * RunnerをBeanとして登録し、ApplicationContextを初期化できるエントリーポイントです。
 */
@SpringBootApplication
public class ApplicationRunnerPracticeApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationRunnerPracticeApplication.class);

    public static void main(String[] args) {
        try {
            var context = SpringApplication.run(ApplicationRunnerPracticeApplication.class, args);
            System.exit(SpringApplication.exit(context));
        } catch (IllegalArgumentException | IllegalStateException exception) {
            LOGGER.error("ApplicationRunnerの処理に失敗しました。", exception);
            System.exit(1);
        }
    }
}
