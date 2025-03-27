package org.example.backend.databaseapi.application.usecase.liga;

import lombok.RequiredArgsConstructor;
import org.example.backend.databaseapi.application.port.in.liga.AltaLigaPort;
import org.example.backend.databaseapi.application.port.out.liga.CreateLigaPort;
import org.example.backend.databaseapi.domain.liga.Liga;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateLigaUseCase implements AltaLigaPort {

    private final CreateLigaPort createLigaPort;

    @Override
    public Liga altaLiga(Liga liga) {
        return createLigaPort.createLiga(liga);
    }
}
