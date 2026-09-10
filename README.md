# spring-boot-application-runner-practice
Spring BootのApplicationRunnerインタフェースを活用したバッチアプリケーション構築練習

## 概要

このプロジェクトは、Spring Bootの`ApplicationRunner`を使い、ApplicationContextの初期化完了後に処理を1回だけ実行する流れを学ぶためのコンソールアプリケーションです。

`--name=Taro`、`--verbose`、非オプション引数を読み取り、引数処理サービスへ委譲します。`--name`に空の値を指定した場合は`IllegalArgumentException`となり、起動失敗として扱います。

## 実行

Java 25を用意し、次のコマンドを実行します。Maven WrapperがMavenを自動取得するため、Mavenの個別インストールは不要です。

```text
.\mvnw.cmd spring-boot:run -Dspring-boot.run.arguments="--name=Taro --verbose input.txt"
```

正常終了時は処理結果がログに出力され、処理完了後にプロセスが終了します。引数が不正な場合はエラーで起動に失敗し、プロセスの終了コードは1です。

期待されるログの例:

```text
INFO ... StartupOrderRunner : startup order step=1
INFO ... CommandLineArgumentsProcessor : name=Taro, verbose=true, nonOptionArgs=[input.txt]
```

`StartupOrderRunner` に `@Order(1)`、`CommandLineArgumentsRunner` に `@Order(2)` を付けているため、
複数のRunnerを登録した場合も実行順序を明示できます。Runnerが1つだけなら順序指定は不要です。

引数エラーの確認:

```text
.\mvnw.cmd spring-boot:run -Dspring-boot.run.arguments="--name="
```

## 学習ポイント

- `CommandLineArgumentsRunner`は`ApplicationRunner`を実装し、Spring Beanとして登録されています。
- `run(ApplicationArguments args)`はApplicationContextの初期化完了後に呼び出されます。
- `containsOption`、`getOptionValues`、`getNonOptionArgs`で、オプションの有無・値・非オプション引数を取得できます。
- `CommandLineArgumentsProcessor`へ処理を委譲することで、Runnerの起動タイミングと引数処理を分離しています。
- `@Order`により、起動ログの順序制御を確認できます。
- 起動テストでは、プロセッサーが1回だけ呼び出されることを確認しています。
