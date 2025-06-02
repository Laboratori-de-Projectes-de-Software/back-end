package com.debateia.adapter.in.rest.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;

public record TokenData(
        @JsonProperty("token") String accessToken,
        Instant expiresIn) {
}
