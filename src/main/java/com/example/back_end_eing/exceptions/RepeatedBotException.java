package com.example.back_end_eing.exceptions;

public class RepeatedBotException extends RuntimeException {
    public RepeatedBotException(String message) {
        super("Bot con API " + message + " ya registrado");
    }
}
