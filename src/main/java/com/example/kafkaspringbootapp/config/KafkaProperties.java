package com.example.kafkaspringbootapp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.kafka")
public record KafkaProperties(
        String bootstrapServers,
        Producer producer,
        Consumer consumer
) {
    // 内部用の Record を定義
    public record Producer(
            String topic,
            String keySerializer,
            String valueSerializer,
            String acks,
            int retries
    ) {}

    public record Consumer(
            boolean enabled,
            String topic,
            String groupId,
            String autoOffsetReset,
            boolean enableAutoCommit,
            String keyDeserializer,
            String valueDeserializer
    ) {}
}