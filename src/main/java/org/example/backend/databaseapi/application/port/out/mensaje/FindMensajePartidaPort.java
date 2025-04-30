package org.example.backend.databaseapi.application.port.out.mensaje;

import org.example.backend.databaseapi.domain.mensaje.Mensaje;

import java.util.List;

public interface FindMensajePartidaPort {

    List<Mensaje> findMensajePartida(Integer idPartida);

}
