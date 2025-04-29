package com.alia.back_end_service.domain.user.exceptions;

public class UsernameAlreadyExistsException extends RuntimeException{
    public UsernameAlreadyExistsException() {
        super("El username ya está registrado");
    }
}
