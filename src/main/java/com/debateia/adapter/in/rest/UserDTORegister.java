package com.debateia.adapter.in.rest;

public record UserDTORegister(
                String email,
                String password,
                String confirmPassword,
                String username) {
}
