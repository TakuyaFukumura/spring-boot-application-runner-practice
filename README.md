# spring-boot-application-runner-practice
Spring BootのApplicationRunnerインタフェースを活用したバッチアプリケーション構築練習

## 概要

このプロジェクトは、Spring Bootの`ApplicationRunner`を使い、ApplicationContextの初期化完了後に処理を1回だけ実行する流れを学ぶためのコンソールアプリケーションです。

`--name=Taro`、`--verbose`、非オプション引数を読み取り、引数処理サービスへ委譲します。`--name`に空の値を指定した場合は`IllegalArgumentException`となり、起動失敗として扱います。

## 実行

Java 25とMavenを用意し、次のコマンドを実行します。

```text
mvn spring-boot:run -Dspring-boot.run.arguments="--name=Taro --verbose input.txt"
```

正常終了時は処理結果がログに出力されます。引数が不正な場合はエラーで起動に失敗し、プロセスの終了コードは1です。

## 学習ポイント

- `CommandLineArgumentsRunner`は`ApplicationRunner`を実装し、Spring Beanとして登録されています。
- `run(ApplicationArguments args)`はApplicationContextの初期化完了後に呼び出されます。
- `CommandLineArgumentsProcessor`へ処理を委譲することで、Runnerの起動タイミングと引数処理を分離しています。
- 起動テストでは、プロセッサーが1回だけ呼び出されることを確認しています。
