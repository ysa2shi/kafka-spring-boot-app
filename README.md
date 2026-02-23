# Kafka Spring Boot App

Spring Boot + Apache Kafka で、HTTP エンドポイントから Kafka にメッセージ送信する PoC アプリです。  
`/kafka/send` では固定文字列を、`/kafka/produce-posts` では外部 API から取得したデータを JSON 形式で送信します。

## 技術スタック

- Java 21
- Spring Boot 3.5.9
- Spring for Apache Kafka 3.3.11
- Docker Compose (Zookeeper, Kafka, Kafka UI)

## 前提条件

- Java 21
- Docker / Docker Compose

## 起動手順

1. Kafka 関連コンテナを起動

```bash
make up
```

2. トピック作成（`quickstart-events`）

```bash
make create-topic
```

3. アプリ起動

```bash
./gradlew bootRun
```

## エンドポイント

### 1. 固定文字列を送信

- Method/Path: `POST /kafka/send`
- 宛先トピック: `quickstart-events`
- 送信値: `"hello kafka"`（文字列）
- レスポンス: `Message sent!`

```bash
curl -X POST http://localhost:8080/kafka/send
```

### 2. 外部 API の Post 一覧を JSON 送信

- Method/Path: `POST /kafka/produce-posts`
- 外部 API: `https://jsonplaceholder.typicode.com/posts`
- 宛先トピック: `quickstart-events`
- キー: `post.userId.value`（文字列）
- 値: `Post` ドメインオブジェクトを `JsonSerializer` でシリアライズした JSON
- レスポンス: `Posts sent to Kafka!`

```bash
curl -X POST http://localhost:8080/kafka/produce-posts
```

送信される JSON 値の例:

```json
{
  "userId": { "value": 1 },
  "id": { "value": 1 },
  "title": { "value": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit" },
  "body": { "value": "quia et suscipit suscipit recusandae..." }
}
```

## メッセージ確認

### Kafka UI

`http://localhost:8081` にアクセスし、`quickstart-events` のメッセージを確認します。

### CLI

```bash
make consume-cli
```

## テスト実行

```bash
./gradlew test
```

## Makefile の主要コマンド

- `make up`: Docker コンテナを起動
- `make down`: Docker コンテナを停止
- `make restart`: コンテナを再起動
- `make logs`: コンテナログを表示
- `make ps`: コンテナ状態を表示
- `make create-topic`: `quickstart-events` を作成
- `make list-topics`: トピック一覧を表示
- `make describe-topic`: `quickstart-events` の詳細を表示
- `make consume-cli`: `quickstart-events` を先頭から購読
