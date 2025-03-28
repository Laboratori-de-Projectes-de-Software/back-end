package org.example.backend.databaseapi.application.exception;

public class IncorrectCredentialsException extends RuntimeException{
    public IncorrectCredentialsException(String s) {
        super(s);
    }
}
