package com.example.kafkaspringbootapp.domain.vo;

public record UserId(Long value) {
    public static UserId of(Long userId) {
        return new UserId(userId);
    }
}
