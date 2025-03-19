package com.debateia.dto_viejo;

public record RegisterRequest(
    String email,
    String password,
    String confirmPassword,
    String name){
}
