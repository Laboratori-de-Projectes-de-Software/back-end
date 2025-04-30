package org.example.backend.databaseapi.application.port.out.liga;

import org.example.backend.databaseapi.domain.liga.Liga;
import java.util.Optional;

public interface CreateLigaPort {

    Optional<Liga> createLiga(Liga liga);
}
