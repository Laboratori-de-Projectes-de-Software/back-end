package com.example.back_end_eing.exceptions;

public class LigaNotFoundException extends RuntimeException {
    public LigaNotFoundException(Long message) {
        super("Bot con id " + message + " no existe");
    }
}
