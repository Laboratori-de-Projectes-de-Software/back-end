package org.example.backend.databaseapi.application.usecase.partida;

import lombok.AllArgsConstructor;
import org.example.backend.databaseapi.application.port.in.partida.AltaPartidaPort;
import org.example.backend.databaseapi.application.port.out.partida.CreatePartidaPort;
import org.example.backend.databaseapi.domain.Liga;
import org.example.backend.databaseapi.domain.Partida;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CreatePartidaUseCase implements AltaPartidaPort {

    private final CreatePartidaPort createPartidaPort;

    @Override
    public Partida altaPartida(Partida partida) {
        return createPartidaPort.createPartida(partida);
    }
}
