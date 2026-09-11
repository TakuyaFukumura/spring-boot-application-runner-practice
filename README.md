# spring-boot-application-runner-practice

Spring Boot の `ApplicationRunner` と DDD の基本構造を学ぶコンソールアプリケーションです。

## 実行

Java 25 と Maven Wrapper を使用します。

```powershell
.\mvnw.cmd spring-boot:run "-Dspring-boot.run.arguments=register --order-id=1001 --product=Book --quantity=2"
.\mvnw.cmd spring-boot:run "-Dspring-boot.run.arguments=list"
```

登録結果と一覧は標準出力、診断情報とエラーはログへ出力します。注文がない場合は
`注文はありません。` と表示されます。不正入力や重複登録は終了コード 1、正常終了は 0 です。

## 構成と学習ポイント

* `presentation`: `ApplicationArguments` をコマンド DTO へ変換し、`ApplicationRunner` を起動する。
  `StartupOrderRunner` は `@Order(1)`、注文 Runner は `@Order(2)` で既存の実行順学習を維持する。
* `application`: register/list の手順、コマンド DTO、出力 DTO を担当する。
* `domain`: `Order` 集約、`OrderId`・`ProductName`・`Quantity`・`OrderLine` と
  `OrderRepository` 抽象を提供する。Spring や CLI には依存しない。
* `infrastructure`: `OrderRepository` のプロセス内インメモリ実装を提供する。

注文 ID・商品名は空値不可、数量は 1 以上、明細は 1 注文 1 件です。同じ ID の登録は
`DuplicateOrderException` になります。コードリーディングの順序と依存方向は
`docs/コードリーディング補助資料.md` を参照してください。

## 検証

```powershell
.\mvnw.cmd clean verify
```

要件定義書の受け入れ基準は実装済みで、Domain/Application 単体テストと Spring Context
Runner テストを含みます。JAR の起動確認は上記コマンドで生成した JAR に対しても実施できます。

GitHub Actions でも、`main` への push と Pull Request を対象に Java 25 で
`mvnw verify` を実行します。
