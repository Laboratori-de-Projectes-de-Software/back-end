package com.debateia.adapter.in.rest.auth;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;

public record AuthenticatedUserDTO(
        @JsonProperty("token") String token,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
        Instant expiresIn,
        String user,
        int id,
        String mail) {
}
