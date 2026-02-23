package com.example.kafkaspringbootapp.infrastructure;

import com.example.kafkaspringbootapp.domain.interfaces.PostProducer;
import com.example.kafkaspringbootapp.domain.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaPostProducer implements PostProducer {

    private final KafkaTemplate<String, Post> kafkaTemplate;

    @Override
    public void send(Post post) {
        kafkaTemplate.send("quickstart-events", String.valueOf(post.userId().value()), post);
        System.out.println("[Kafka] Sent post id=" + post.id().value() + " userId=" + post.userId().value());
    }
}
