package com.example.kafkaspringbootapp.consumer.resource;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ConsumerMessageResource(
        @JsonProperty("userId") Long userId,
        @JsonProperty("id") Long id,
        @JsonProperty("title") String title,
        @JsonProperty("body") String body
) {}