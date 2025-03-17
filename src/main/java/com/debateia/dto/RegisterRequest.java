package com.debateia.dto;

public record RegisterRequest(
    String email,
    String password,
    String confirmPassword,
    String name){
}
