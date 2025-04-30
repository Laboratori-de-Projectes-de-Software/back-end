package org.example.backend.databaseapi.application.port.out.mensaje;

import org.example.backend.databaseapi.domain.mensaje.Mensaje;

public interface CreateMensajePort {

    Mensaje createMensaje(Mensaje mensaje);

}
