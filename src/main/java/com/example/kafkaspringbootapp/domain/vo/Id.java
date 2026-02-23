package com.example.kafkaspringbootapp.domain.vo;

public record Id(Long value) {
    public static Id of(Long id) {
        return new Id(id);
    }
}
