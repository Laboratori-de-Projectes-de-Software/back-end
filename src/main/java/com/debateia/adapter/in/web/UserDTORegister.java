package com.debateia.adapter.in.web;

public record UserDTORegister(
        String email,
        String password,
        String confirmPassword,
        String username) {
}
