package org.example.backend.databaseapi.application.port.in.usuario;

public interface RecuperarPasswordPort {
    boolean procesarSolicitudRecuperacion(String email);
    boolean validarTokenRecuperacion(String token);
    boolean cambiarPassword(String token, String nuevaPassword);
}
