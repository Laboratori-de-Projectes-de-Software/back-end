package org.example.backend.databaseapi.application.usecase.liga;

import lombok.AllArgsConstructor;
import org.example.backend.databaseapi.application.port.in.liga.ActualizarLigaPort;
import org.example.backend.databaseapi.application.port.out.liga.UpdateLigaPort;
import org.example.backend.databaseapi.domain.liga.Liga;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ActualizarLigaUseCase implements ActualizarLigaPort {

    private final UpdateLigaPort updateLigaPort;

    @Override
    public Liga actualizarLiga(Liga liga, Integer id) {
        return updateLigaPort.updateLiga(liga,id);
    }
}
