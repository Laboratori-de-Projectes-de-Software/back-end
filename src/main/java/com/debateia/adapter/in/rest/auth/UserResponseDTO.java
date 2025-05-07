package com.debateia.adapter.in.rest.auth;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;

public record UserResponseDTO(
        @JsonProperty("token") String accessToken,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
        Instant expiresIn,
        String user,
        int userId) {
}
