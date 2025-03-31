package com.debateia.adapter.in.rest;

public record AuthRequest(
        String email,
        String password
) {
}
