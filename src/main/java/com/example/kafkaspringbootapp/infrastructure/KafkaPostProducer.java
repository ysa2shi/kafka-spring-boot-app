package com.example.kafkaspringbootapp.infrastructure;

import com.example.kafkaspringbootapp.config.KafkaProperties;
import com.example.kafkaspringbootapp.domain.interfaces.PostProducer;
import com.example.kafkaspringbootapp.domain.model.Post;
import com.example.kafkaspringbootapp.infrastructure.resource.ProducerMessageResource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaPostProducer implements PostProducer {

    private final KafkaProperties kafkaProperties;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void send(Post post) {
        ProducerMessageResource message = new ProducerMessageResource(
                post.userId().value(),
                post.id().value(),
                post.title().value(),
                post.body().value()
        );
        kafkaTemplate.send(kafkaProperties.producer().topic(),String.valueOf(message.userId()), message);
        log.info("[Kafka Producer] Sent post id={} userId={}", message.id(), message.userId());
    }
}
