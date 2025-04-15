package com.example.back_end_eing.exceptions;

public class BotLimitReachedException extends RuntimeException {
    public BotLimitReachedException(String message) {
        super(message);
    }
}
