package org.example.backend.databaseapi.application.port.out.liga;

import org.example.backend.databaseapi.domain.liga.Liga;

public interface DeleteLigaPort {

    Liga deleteLiga(Integer ligaId,Integer userId);
}
