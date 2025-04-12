package com.example.back_end_eing.exceptions;

import lombok.Builder;
import lombok.Getter;

@Getter
public enum ErrorCodes {
    USER_ALREADY_EXISTS(11000, "USER_ALREADY_EXISTS", "El usuario ya existe"),
    EMAIL_ALREADY_EXISTS(11001, "EMAIL_ALREADY_EXISTS", "Este correo ya está registrado"),
    USER_NAME_NOT_FOUND(11002, "USER_NAME_NOT_FOUND", "Este nombre de usuario no está asociado a ningún usuario"),
    INCORRECT_PASSWORD(11003, "INCORRECT_PASSWORD", "Esta contraseña no es correcta"),
    BOT_ALREADY_EXISTS(11004, "BOT_ALREADY_EXISTS", "Bot ya registrado"),
    USER_NOT_FOUND(11005, "USER_NOT_FOUND", "Usuario no registrado"),
    BOT_NOT_FOUND(11006, "BOT_NOT_FOUND", "Bot no registrado"),
    LEAGUE_NOT_FOUND(11007, "LEAGUE_NOT_FOUND", "Liga no registrada"),
    INCORRECT_NUM_BOTS(11008, "INCORRECT_NUM_BOTS", "Numero incorrecto de bots asignados al registro de la liga"),
    CLASIFICATION_NOT_FOUND(11009, "CLASIFICATION_NOT_FOUND", "Clasificación inexistente");

    private final int code;
    private final String type;
    private final String message;

    ErrorCodes(int code, String type, String message) {
        this.code = code;
        this.type = type;
        this.message = message;
    }
}
