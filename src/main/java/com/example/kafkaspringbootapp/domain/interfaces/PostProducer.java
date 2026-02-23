package com.example.kafkaspringbootapp.domain.interfaces;

import com.example.kafkaspringbootapp.domain.model.Post;

public interface PostProducer {
    void send(Post post);
}
