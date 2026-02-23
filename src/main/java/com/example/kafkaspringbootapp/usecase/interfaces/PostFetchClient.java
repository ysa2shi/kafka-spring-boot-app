package com.example.kafkaspringbootapp.usecase.interfaces;

import com.example.kafkaspringbootapp.infrastructure.resource.PostResponseResource;

import java.util.List;

public interface PostFetchClient {
    /**
     * 外部APIからPostを取得する
     * @return Postのリスト
     */
    List<PostResponseResource> fetchPosts();
}