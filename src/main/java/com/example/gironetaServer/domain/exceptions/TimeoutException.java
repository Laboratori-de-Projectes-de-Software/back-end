package com.example.gironetaServer.domain.exceptions;

public class TimeoutException extends RuntimeException {
    public TimeoutException(String message) {
        super(message);
    }
}