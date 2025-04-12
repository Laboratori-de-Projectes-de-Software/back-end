package com.example.back_end_eing.exceptions;


public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(int id) {
        super("Usuario con id " + id + " no encontrado");
    }
}

