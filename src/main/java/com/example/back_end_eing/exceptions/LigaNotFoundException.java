package com.example.back_end_eing.exceptions;

public class LigaNotFoundException extends RuntimeException {
    public LigaNotFoundException(Long message) {
        super("Liga con id " + message + " no existe");
    }
}
