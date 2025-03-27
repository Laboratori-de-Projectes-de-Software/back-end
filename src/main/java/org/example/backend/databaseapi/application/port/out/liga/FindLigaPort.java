package org.example.backend.databaseapi.application.port.out.liga;

import org.example.backend.databaseapi.domain.liga.Liga;

import java.util.Optional;

public interface FindLigaPort {

    Optional<Liga> findLiga(int id_liga);

}
