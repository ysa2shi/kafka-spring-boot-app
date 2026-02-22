# Kafka Spring Boot App

Spring Boot 4.0.3 と Apache Kafka を使用した、メッセージプロデューサーのサンプルアプリケーションです。

## 技術スタック

- **Java**: 21
- **Framework**: Spring Boot 4.0.3
- **Messaging**: Apache Kafka
- **Infrastructure**: Docker Compose (Zookeeper, Kafka, Kafka UI)

## 前提条件

- Java 21
- Docker / Docker Compose

## セットアップ

### 1. インフラストラクチャの起動

Docker Compose を使用して Kafka クラスターと管理ツールを起動します。

```bash
make up
```

以下のツールが起動します：
- **Kafka**: localhost:9092
- **Zookeeper**: localhost:2181
- **Kafka UI**: [http://localhost:8081](http://localhost:8081)

### 2. トピックの作成

メッセージ送信に使用するトピック `quickstart-events` を作成します。

```bash
make create-topic
```

### 3. アプリケーションの実行

```bash
./gradlew bootRun
```

## 使い方

### メッセージの送信

`/kafka/send` エンドポイントに対して POST リクエストを送信すると、Kafka にメッセージが送信されます。

```bash
curl -X POST http://localhost:8080/kafka/send
```

送信に成功すると `Message sent!` というレスポンスが返ります。

### メッセージの確認

#### Kafka UI で確認する
ブラウザで [http://localhost:8081](http://localhost:8081) にアクセスし、トピック `quickstart-events` の内容を確認できます。

#### CLI で確認する
以下のコマンドで、トピックに送信されたメッセージをコンソールに出力できます。

```bash
make consume-cli
```

## Makefile の主要コマンド

- `make up`: Docker コンテナを起動
- `make down`: Docker コンテナを停止
- `make create-topic`: トピックを作成
- `make list-topics`: トピック一覧を表示
- `make consume-cli`: トピックのメッセージをコンソールで受信
