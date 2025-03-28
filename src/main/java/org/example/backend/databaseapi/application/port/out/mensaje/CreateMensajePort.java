package org.example.backend.databaseapi.application.port.out.mensaje;

import org.example.backend.databaseapi.domain.Bot;
import org.example.backend.databaseapi.domain.Mensaje;
import org.example.backend.databaseapi.domain.Partida;

public interface CreateMensajePort {

    Mensaje createMensaje(Mensaje mensaje);

}
