package com.example.back_end_eing.exceptions;


public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long message) {
        super("Usuario con id " + message + " no encontrado");
    }

}
