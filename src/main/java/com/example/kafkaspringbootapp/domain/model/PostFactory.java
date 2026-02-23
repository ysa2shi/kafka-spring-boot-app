package com.example.kafkaspringbootapp.domain.model;

import com.example.kafkaspringbootapp.domain.vo.Body;
import com.example.kafkaspringbootapp.domain.vo.Id;
import com.example.kafkaspringbootapp.domain.vo.Title;
import com.example.kafkaspringbootapp.domain.vo.UserId;
import org.springframework.stereotype.Component;

@Component
public class PostFactory {
    public Post create(
            Long userId,
            Long id,
            String title,
            String body
    ) {
        return Post.builder()
                .userId(UserId.of(userId))
                .id(Id.of(id))
                .title(Title.of(title))
                .body(Body.of(body))
                .build();
    }
}
