package com.alia.back_end_service.domain.bot.exceptions;

public class BotAlreadyExistsException extends RuntimeException {
    public BotAlreadyExistsException(String message) {
        super(message);
    }
}
