package com.alia.back_end_service.domain.user.exceptions;

public class EmailAlreadyExistsException extends RuntimeException{
    public EmailAlreadyExistsException() {
        super("El email ya está registrado");
    }
}
