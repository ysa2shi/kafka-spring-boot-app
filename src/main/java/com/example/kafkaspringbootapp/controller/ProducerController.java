package com.example.kafkaspringbootapp.controller;

import com.example.kafkaspringbootapp.usecase.PostApplicationUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/kafka")
public class ProducerController {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final PostApplicationUseCase postApplicationUseCase;

    /**
     * 単純な文字列メッセージをKafkaに送信するエンドポイント
     * @return 成功レスポンス
     */
    @PostMapping("/send")
    public String send() {
        kafkaTemplate.send("quickstart-events", "hello kafka");
        return "Message sent!";
    }

    /**
     * 外部APIから取得したPostをKafkaに送信するエンドポイント
     * @return 成功レスポンス
     */
    @PostMapping("/produce-posts")
    public String producePosts() {
        postApplicationUseCase.fetchAndSendPosts();
        return "Posts sent to Kafka!";
    }
}