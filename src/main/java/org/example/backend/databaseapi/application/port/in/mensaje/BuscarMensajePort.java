package org.example.backend.databaseapi.application.port.in.mensaje;

import org.example.backend.databaseapi.domain.Mensaje;

public interface BuscarMensajePort {

    Mensaje buscarMensaje(Integer idMensaje);
}
