package com.example.kafkaspringbootapp.domain.vo;

public record Title(String value) {
    public static Title of(String title) {
        return new Title(title);
    }
}
