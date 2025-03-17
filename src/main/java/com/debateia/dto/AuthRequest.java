package com.debateia.dto;

public record AuthRequest(
        String email,
        String password
) {
}
