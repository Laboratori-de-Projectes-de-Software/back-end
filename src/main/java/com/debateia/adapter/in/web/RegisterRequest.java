package com.debateia.adapter.in.web;

public record RegisterRequest(
    String email,
    String password,
    String confirmPassword,
    String name){
}
