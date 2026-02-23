package com.example.kafkaspringbootapp.domain.vo;

public record Body(String value) {
    public static Body of(String body) {
        return new Body(body);
    }
}
