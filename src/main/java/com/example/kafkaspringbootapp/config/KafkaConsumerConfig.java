package com.example.kafkaspringbootapp.config;

import com.example.kafkaspringbootapp.consumer.resource.ConsumerMessageResource;
import com.example.kafkaspringbootapp.domain.model.Post;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;


@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties({KafkaProperties.class})
public class KafkaConsumerConfig {

    private final KafkaProperties kafkaProperties;

    @Bean
    public ConsumerFactory<String, ConsumerMessageResource> consumerFactory() {
        Map<String, Object> config = new HashMap<>(kafkaProperties.buildConsumerProperties());

        // trusted packages を必ず指定
        config.put(JsonDeserializer.TRUSTED_PACKAGES, "com.example.kafkaspringbootapp.consumer.resource");

        // 必須ではないが、キーは String なので型マッパーを無効化
        config.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, false);

        return new DefaultKafkaConsumerFactory<>(
                config,
                new StringDeserializer(),
                new JsonDeserializer<>(ConsumerMessageResource.class, false) // falseで型情報をHeaderから期待しない
                        .trustedPackages("com.example.kafkaspringbootapp.consumer.resource")
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ConsumerMessageResource> postKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ConsumerMessageResource> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}