package org.example.backend.databaseapi.application.port.in.liga;

import org.example.backend.databaseapi.domain.liga.Liga;

public interface EliminarLigaPort {

    Liga eliminarLiga(Integer ligaId,Integer userId);
}
