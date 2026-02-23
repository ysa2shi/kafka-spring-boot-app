KAFKA_CONTAINER=kafka
BOOTSTRAP=localhost:9092
TOPIC=quickstart-events
PARTITIONS=3
REPLICATION_FACTOR=1

up:
	docker compose up -d

down:
	docker compose down

restart: down up

logs:
	docker compose logs -f

ps:
	docker compose ps

create-topic:
	docker exec -it $(KAFKA_CONTAINER) kafka-topics \
	--create \
	--if-not-exists \
	--bootstrap-server $(BOOTSTRAP) \
	--topic $(TOPIC) \
	--partitions $(PARTITIONS) \
	--replication-factor $(REPLICATION_FACTOR)

list-topics:
	docker exec -it $(KAFKA_CONTAINER) kafka-topics \
	--list \
	--bootstrap-server $(BOOTSTRAP)

describe-topic:
	docker exec -it $(KAFKA_CONTAINER) kafka-topics \
	--describe \
	--topic $(TOPIC) \
	--bootstrap-server $(BOOTSTRAP)

consume-cli:
	docker exec -it $(KAFKA_CONTAINER) kafka-console-consumer \
	--topic $(TOPIC) \
	--from-beginning \
	--bootstrap-server $(BOOTSTRAP)