package org.example.backend.databaseapi.application.port.out.mensaje;

import org.example.backend.databaseapi.domain.Mensaje;

import java.util.Optional;

public interface FindMensajePort {

    Optional<Mensaje> findMensaje(Integer idMensaje);

}
