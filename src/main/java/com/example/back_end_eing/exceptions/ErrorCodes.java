package com.example.back_end_eing.exceptions;

import lombok.Builder;
import lombok.Getter;

@Getter
public enum ErrorCodes {
    USER_ALREADY_EXISTS(11000, "USER_ALREADY_EXISTS", "El usuario ya existe"),
    EMAIL_ALREADY_EXISTS(11001, "EMAIL_ALREADY_EXISTS", "Este correo ya está registrado");;

    private final int code;
    private final String type;
    private final String message;

    ErrorCodes(int code, String type, String message) {
        this.code = code;
        this.type = type;
        this.message = message;
    }
}
