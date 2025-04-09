package com.debateia.adapter.out.persistence;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserResponseDTO(
        @JsonProperty("access_token") String accessToken,
        int expiration,
        String user,
        int userId) {
}
