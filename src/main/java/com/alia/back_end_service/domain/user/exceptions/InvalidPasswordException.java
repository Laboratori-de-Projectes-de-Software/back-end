package com.alia.back_end_service.domain.user.exceptions;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException() {
        super("Contraseña incorrecta");
    }
}

