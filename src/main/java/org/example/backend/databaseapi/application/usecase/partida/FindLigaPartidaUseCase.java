package org.example.backend.databaseapi.application.usecase.partida;

import lombok.AllArgsConstructor;
import org.example.backend.databaseapi.application.port.in.partida.BuscarPartidasLigaPort;
import org.example.backend.databaseapi.application.port.out.partida.FindLigaPartidaPort;
import org.example.backend.databaseapi.domain.Partida;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class FindLigaPartidaUseCase implements BuscarPartidasLigaPort {

    private final FindLigaPartidaPort findLigaPartidaPort;

    @Override
    public List<Partida> buscarLigaPartida(Integer idliga) {
        return findLigaPartidaPort.findLigaPartida(idliga);
    }
}
