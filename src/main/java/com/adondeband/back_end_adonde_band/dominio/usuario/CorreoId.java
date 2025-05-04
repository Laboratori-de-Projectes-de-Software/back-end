package com.adondeband.back_end_adonde_band.dominio.usuario;

public record CorreoId(String value) {
    public CorreoId {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("El correo no puede ser nulo o vacío");
        }
        if (!value.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new IllegalArgumentException("El correo no es válido");
        }
    }
    @Override
    public String toString() {
        return value;
    }
}
