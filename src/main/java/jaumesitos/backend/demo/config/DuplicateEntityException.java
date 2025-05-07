package jaumesitos.backend.demo.config;

public class DuplicateEntityException extends RuntimeException{
    public DuplicateEntityException(String message) {
        super(message);
    }

    public DuplicateEntityException(String message, Throwable cause) {
        super(message, cause);
    }
}
