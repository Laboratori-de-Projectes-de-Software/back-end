package org.example.backend.databaseapi.application.port.in.mensaje;

import org.example.backend.databaseapi.domain.Mensaje;

import java.util.List;

public interface BuscarMensajesBotPort {

    List<Mensaje> buscarMensajesBot(Integer botId);
}
