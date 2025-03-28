package org.example.backend.databaseapi.application.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String s) {
        super(s);
    }
}
