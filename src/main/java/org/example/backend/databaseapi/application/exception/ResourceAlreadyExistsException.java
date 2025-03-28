package org.example.backend.databaseapi.application.exception;

public class ResourceAlreadyExistsException extends RuntimeException{
    public ResourceAlreadyExistsException(String s) {
        super(s);
    }
}
