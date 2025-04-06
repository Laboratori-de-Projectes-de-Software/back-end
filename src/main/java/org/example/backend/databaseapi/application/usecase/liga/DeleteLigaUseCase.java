package org.example.backend.databaseapi.application.usecase.liga;

import lombok.AllArgsConstructor;
import org.example.backend.databaseapi.application.port.in.liga.EliminarLigaPort;
import org.example.backend.databaseapi.application.port.out.liga.DeleteLigaPort;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DeleteLigaUseCase implements EliminarLigaPort {

    private final DeleteLigaPort deleteLigaPort;

    @Override
    public void eliminarLiga(Integer ligaId) {
        deleteLigaPort.deleteLiga(ligaId);
    }
}
