package com.example.kafkaspringbootapp.infrastructure.resource;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 外部APIから受け取るPostリソース
 */
public record PostResponseResource(
        @JsonProperty("userId") Long userId,
        @JsonProperty("id") Long id,
        @JsonProperty("title") String title,
        @JsonProperty("body") String body
) {}