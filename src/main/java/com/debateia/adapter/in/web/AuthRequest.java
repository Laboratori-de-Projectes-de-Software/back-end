package com.debateia.adapter.in.web;

public record AuthRequest(
        String email,
        String password
) {
}
