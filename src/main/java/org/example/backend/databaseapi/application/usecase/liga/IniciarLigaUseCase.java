package org.example.backend.databaseapi.application.usecase.liga;

import lombok.AllArgsConstructor;
import org.example.backend.databaseapi.application.port.in.liga.IniciarLigaPort;
import org.example.backend.databaseapi.application.port.in.partida.AltaPartidaPort;
import org.example.backend.databaseapi.application.port.out.liga.FindLigaPort;
import org.example.backend.databaseapi.application.port.out.partida.CreatePartidaPort;
import org.example.backend.databaseapi.application.usecase.resultado.CreateResultadoUseCase;
import org.example.backend.databaseapi.domain.bot.BotId;
import org.example.backend.databaseapi.domain.liga.Liga;
import org.example.backend.databaseapi.domain.liga.LigaId;
import org.example.backend.databaseapi.domain.partida.Estado;
import org.example.backend.databaseapi.domain.partida.Partida;
import org.example.backend.databaseapi.domain.resultado.Resultado;
import org.example.backend.databaseapi.domain.resultado.ResultadoId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class IniciarLigaUseCase implements IniciarLigaPort {

    private FindLigaPort findLigaPort;
    private AltaPartidaPort altaPartidaPort;
    private CreateResultadoUseCase createResultadoUseCase;

    @Override
    public void iniciarLiga(Integer ligaId) {
        Liga liga=findLigaPort.findLiga(ligaId)
                .orElseThrow();

        List<BotId> bots=liga.getBotsLiga();

        for(int i=0;i<bots.size();i++){
            for(int j=i+1;j<bots.size();j++){
                Partida partida=Partida.builder()
                        .liga(new LigaId(ligaId))
                        .estado(Estado.PENDANT)
                        .roundNumber(j)
                        .duracionTotal(liga.getMatchTime())

                        .build();
                partida=altaPartidaPort.altaPartida(partida);

                Resultado resultado=Resultado.builder()
                        .puntuacion(null)
                        .resultadoId(new ResultadoId(bots.get(i).value(),partida.getPartidaId().value()))
                        .build();
                createResultadoUseCase.crearResultado(resultado);

                resultado=Resultado.builder()
                        .puntuacion(null)
                        .resultadoId(new ResultadoId(bots.get(j).value(),partida.getPartidaId().value()))
                        .build();
                createResultadoUseCase.crearResultado(resultado);
            }
        }
    }
}
