package com.example.kafkaspringbootapp.infrastructure.external;

import com.example.kafkaspringbootapp.infrastructure.resource.PostResponseResource;
import com.example.kafkaspringbootapp.usecase.interfaces.PostFetchClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Arrays;
import java.util.List;

@Component
public class PostFetchClientImp implements PostFetchClient {

    private final RestClient restClient;

    public PostFetchClientImp() {
        this.restClient = RestClient.builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .build();
    }

    @Override
    public List<PostResponseResource> fetchPosts() {
        PostResponseResource[] resources = restClient.get()
                .uri("/posts")
                .retrieve()
                .body(PostResponseResource[].class);;
        return Arrays.asList(resources);
    }
}