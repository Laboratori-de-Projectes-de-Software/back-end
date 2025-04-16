package com.alia.back_end_service.domain.league.exceptions;

public class BotAlreadyRegisteredInALeague extends RuntimeException {
    public BotAlreadyRegisteredInALeague(String message) {
        super(message);
    }
}
