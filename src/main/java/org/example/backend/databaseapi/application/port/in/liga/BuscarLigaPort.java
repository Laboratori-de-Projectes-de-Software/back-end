package org.example.backend.databaseapi.application.port.in.liga;

import org.example.backend.databaseapi.domain.Liga;

public interface BuscarLigaPort {

    Liga buscarLiga(Integer ligaId);

}
