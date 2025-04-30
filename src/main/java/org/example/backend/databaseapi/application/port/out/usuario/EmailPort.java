package org.example.backend.databaseapi.application.port.out.usuario;

public interface EmailPort {
    void enviarCorreoRecuperacion(String destinatario, String token);
}
