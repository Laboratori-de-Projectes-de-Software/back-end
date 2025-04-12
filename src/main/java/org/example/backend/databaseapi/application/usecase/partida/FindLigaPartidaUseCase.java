package org.example.backend.databaseapi.application.usecase.partida;

import lombok.AllArgsConstructor;
import org.example.backend.databaseapi.application.dto.partida.MatchDTOResponse;
import org.example.backend.databaseapi.application.port.in.partida.BuscarPartidasLigaPort;
import org.example.backend.databaseapi.application.port.out.bot.FindBotPort;
import org.example.backend.databaseapi.application.port.out.partida.FindLigaPartidaPort;
import org.example.backend.databaseapi.application.port.out.resultado.FindBotResultadoPort;
import org.example.backend.databaseapi.application.port.out.resultado.FindPartidaResultadoPort;
import org.example.backend.databaseapi.application.port.out.resultado.FindResultadoPort;
import org.example.backend.databaseapi.domain.bot.Bot;
import org.example.backend.databaseapi.domain.partida.Partida;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FindLigaPartidaUseCase implements BuscarPartidasLigaPort {

    private final FindLigaPartidaPort findLigaPartidaPort;
    private final FindPartidaResultadoPort findPartidaResultadoPort;
    private final FindBotPort findBotPort;

    @Override
    public List<MatchDTOResponse> buscarLigaPartida(Integer idliga) {
        return findLigaPartidaPort.findLigaPartida(idliga)
                .stream()
                .map(partida ->
                        new MatchDTOResponse(
                            partida.getPartidaId().value(),
                            partida.getEstado().toString(),
                            partida.getResult(),
                            findPartidaResultadoPort.findPartidaResultado(idliga)
                                    .stream()
                                    .map(resultado -> findBotPort.findBot(resultado.getResultadoId().botvalue())
                                            .orElseThrow().getNombre()

                                    )
                                    .toList(),
                            partida.getRoundNumber()
                        )
                )
                .toList();
    }
}
