package com.example.applicationrunner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * コンポーネント探索と自動設定を有効にする、コンソールアプリケーションのエントリーポイントです。
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
