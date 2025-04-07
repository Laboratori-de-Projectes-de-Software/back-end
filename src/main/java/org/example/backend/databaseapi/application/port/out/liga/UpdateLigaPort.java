package org.example.backend.databaseapi.application.port.out.liga;

import org.example.backend.databaseapi.domain.liga.Liga;

public interface UpdateLigaPort {

    Liga updateLiga(Liga liga,Integer id);
}
