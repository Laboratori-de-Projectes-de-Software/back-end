package com.debateia.adapter.in.rest;

public record RegisterRequest(
    String email,
    String password,
    String confirmPassword,
    String name){
}
