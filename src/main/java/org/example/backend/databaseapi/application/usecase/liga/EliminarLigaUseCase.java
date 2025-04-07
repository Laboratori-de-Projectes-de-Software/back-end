package org.example.backend.databaseapi.application.usecase.liga;

import lombok.AllArgsConstructor;
import org.example.backend.databaseapi.application.port.in.liga.EliminarLigaPort;
import org.example.backend.databaseapi.application.port.out.liga.DeleteLigaPort;
import org.example.backend.databaseapi.domain.liga.Liga;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EliminarLigaUseCase implements EliminarLigaPort {

    private final DeleteLigaPort deleteLigaPort;

    @Override
    public Liga eliminarLiga(Integer ligaId) {
        return deleteLigaPort.deleteLiga(ligaId);
    }
}
