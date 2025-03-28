package com.example.back_end_eing.exceptions;


public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message) {
        super("El usuario  " + message + " ya existe");
    }
}
