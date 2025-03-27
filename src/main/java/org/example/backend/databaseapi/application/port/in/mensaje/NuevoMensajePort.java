package org.example.backend.databaseapi.application.port.in.mensaje;

import org.example.backend.databaseapi.domain.mensaje.Mensaje;

public interface NuevoMensajePort {

    Mensaje altaMensaje(Mensaje mensaje);
}
