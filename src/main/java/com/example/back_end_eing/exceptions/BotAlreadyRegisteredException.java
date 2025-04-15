package com.example.back_end_eing.exceptions;

public class BotAlreadyRegisteredException extends RuntimeException {
    public BotAlreadyRegisteredException(String message) {
        super(message);
    }
}
