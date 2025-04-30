package org.example.backend.databaseapi.application.port.in.liga;

import org.example.backend.databaseapi.domain.liga.Liga;

public interface ActualizarLigaPort {

    Liga actualizarLiga(Liga liga,Integer id);
}
