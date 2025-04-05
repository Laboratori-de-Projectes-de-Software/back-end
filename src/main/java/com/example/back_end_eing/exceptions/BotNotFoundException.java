package com.example.back_end_eing.exceptions;

public class BotNotFoundException extends RuntimeException {
    public BotNotFoundException(Long message) {
        super("Bot con id " + message + " no existe");
    }
}
