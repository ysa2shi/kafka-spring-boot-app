package com.example.kafkaspringbootapp.domain.model;

import com.example.kafkaspringbootapp.domain.vo.Body;
import com.example.kafkaspringbootapp.domain.vo.Id;
import com.example.kafkaspringbootapp.domain.vo.Title;
import com.example.kafkaspringbootapp.domain.vo.UserId;
import lombok.Builder;

@Builder
public record Post(
        UserId userId,
        Id id,
        Title title,
        Body body
) {
}
