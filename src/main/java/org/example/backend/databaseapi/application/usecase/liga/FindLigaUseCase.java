package org.example.backend.databaseapi.application.usecase.liga;

import lombok.AllArgsConstructor;
import org.example.backend.databaseapi.application.port.in.liga.BuscarLigaPort;
import org.example.backend.databaseapi.application.port.out.liga.FindLigaPort;
import org.example.backend.databaseapi.domain.liga.Liga;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FindLigaUseCase implements BuscarLigaPort {

    private final FindLigaPort findLigaPort;

    @Override
    public Liga buscarLiga(Integer ligaId) {
        return findLigaPort.findLiga(ligaId)
                .orElseThrow(()->new RuntimeException("No se ha encontrado la liga"));
    }
}
