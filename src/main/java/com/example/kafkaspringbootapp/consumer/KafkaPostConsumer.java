package com.example.kafkaspringbootapp.consumer;

import com.example.kafkaspringbootapp.consumer.resource.ConsumerMessageResource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "spring.kafka.consumer", name = "enabled", havingValue = "true")
@Slf4j
public class KafkaPostConsumer {

    @KafkaListener(
            topics = "${spring.kafka.consumer.topic}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "postKafkaListenerContainerFactory"
    )
    public void listen(ConsumerRecord<String, ConsumerMessageResource> record) {
        ConsumerMessageResource message = record.value();
        log.info(
                "[Kafka Consumer] Received topic={} partition={} offset={} key={} id={} userId={}",
                record.topic(),
                record.partition(),
                record.offset(),
                record.key(),
                message != null ? message.id() : null,
                message != null ? message.userId() : null
        );
    }
}
