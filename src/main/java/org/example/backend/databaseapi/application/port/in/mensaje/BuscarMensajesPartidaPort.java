package org.example.backend.databaseapi.application.port.in.mensaje;

import org.example.backend.databaseapi.domain.mensaje.Mensaje;

import java.util.List;

public interface BuscarMensajesPartidaPort {

    List<Mensaje> buscarMensajesPartida(Integer idPartida);

}
