package com.debateia.adapter.in.web.dto.response;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserResponseDTO(
        @JsonProperty("token") String accessToken,
        LocalDate expiresIn,
        String user,
        int userId) {
}
