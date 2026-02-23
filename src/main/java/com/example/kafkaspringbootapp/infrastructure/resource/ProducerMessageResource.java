package com.example.kafkaspringbootapp.infrastructure.resource;

/**
 * Kafka送信用のデータ転送オブジェクト (DTO)
 */
public record ProducerMessageResource(
        Long userId,
        Long id,
        String title,
        String body
) {}
