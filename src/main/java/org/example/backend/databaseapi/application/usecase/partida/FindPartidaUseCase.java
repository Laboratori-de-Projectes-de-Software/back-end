package org.example.backend.databaseapi.application.usecase.partida;

import lombok.AllArgsConstructor;
import org.example.backend.databaseapi.application.port.in.partida.BuscarPartidaPort;
import org.example.backend.databaseapi.application.port.out.partida.FindPartidaPort;
import org.example.backend.databaseapi.domain.Partida;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class FindPartidaUseCase implements BuscarPartidaPort {

    private final FindPartidaPort findPartidaPort;

    @Override
    public Partida buscarPartida(Integer idPartida) {
        return findPartidaPort.findParida(idPartida)
                .orElseThrow(()->new RuntimeException("No se ha encontrado la partida"));
    }
}
