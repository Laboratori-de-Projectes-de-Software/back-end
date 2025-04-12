package com.example.back_end_eing.exceptions;

public class EnfrentamientoNotFoundException extends RuntimeException {
    public EnfrentamientoNotFoundException(Long id) {
        super("Enfrentamiento con id " + id + " no encontrado");
    }
}
