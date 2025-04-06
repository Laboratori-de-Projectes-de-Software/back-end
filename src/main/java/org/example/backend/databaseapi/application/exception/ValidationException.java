package org.example.backend.databaseapi.application.exception;

public class ValidationException extends RuntimeException{
    public ValidationException(String s) {
        super(s);
    }
}
