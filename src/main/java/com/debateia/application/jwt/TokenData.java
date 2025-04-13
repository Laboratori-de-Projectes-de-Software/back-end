package com.debateia.application.jwt;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record TokenData(
        @JsonProperty("token") String accessToken,
        LocalDate expiresIn) {
}
