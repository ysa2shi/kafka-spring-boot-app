package com.example.kafkaspringbootapp.usecase.interfaces;

import com.example.kafkaspringbootapp.infrastructure.resource.PostResource;

import java.util.List;

public interface PostFetchClient {
    /**
     * 外部APIからPostを取得する
     * @return Postのリスト
     */
    List<PostResource> fetchPosts();
}