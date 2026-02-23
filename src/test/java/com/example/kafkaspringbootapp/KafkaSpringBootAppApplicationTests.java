package com.example.kafkaspringbootapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@EmbeddedKafka(partitions = 1)
@DirtiesContext
class KafkaSpringBootAppApplicationTests {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    void testSend() {
        com.example.kafkaspringbootapp.domain.model.Post post = com.example.kafkaspringbootapp.domain.model.Post.builder()
                .id(new com.example.kafkaspringbootapp.domain.vo.Id(1L))
                .userId(new com.example.kafkaspringbootapp.domain.vo.UserId(1L))
                .title(new com.example.kafkaspringbootapp.domain.vo.Title("test"))
                .body(new com.example.kafkaspringbootapp.domain.vo.Body("test"))
                .build();
        kafkaTemplate.send("quickstart-events", "test-key", post);
    }
}
