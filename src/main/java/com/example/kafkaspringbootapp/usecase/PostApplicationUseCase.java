package com.example.kafkaspringbootapp.usecase;

import com.example.kafkaspringbootapp.domain.model.PostFactory;
import com.example.kafkaspringbootapp.usecase.interfaces.PostFetchClient;
import com.example.kafkaspringbootapp.infrastructure.KafkaPostProducer;
import com.example.kafkaspringbootapp.infrastructure.resource.PostResource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostApplicationUseCase {

    private final PostFetchClient postFetchClient;
    private final PostFactory postFactory;
    private final KafkaPostProducer kafkaPostProducer;

    public void fetchAndSendPosts() {
        // 1. 外部APIからDTOを取得
        List<PostResource> resources = postFetchClient.fetchPosts();

        // 2. Factoryでドメインに変換
        resources.stream()
                .map(resource -> postFactory.create(
                        resource.userId(),
                        resource.id(),
                        resource.title(),
                        resource.body()
                ))
                .forEach(kafkaPostProducer::send);

    }
}