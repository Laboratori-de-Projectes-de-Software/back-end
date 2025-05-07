package com.debateia.adapter.in.rest.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;

import java.time.LocalDate;

public record TokenData(
        @JsonProperty("token") String accessToken,
        Instant expiresIn) {
}
