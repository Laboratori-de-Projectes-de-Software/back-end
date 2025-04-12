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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

        List<BotId> rotatingBots=liga.getBotsLiga().subList(1,liga.getBotsLiga().size());
        int count=liga.getBotsLiga().size();
        if(count%2==1){
            rotatingBots.add(new BotId(0));
            count++;
        }
        for (int ronda=1;ronda<count;ronda++){
            List<BotId> fixedBots = new ArrayList<>();
            fixedBots.add(liga.getBotsLiga().getFirst());
            fixedBots.addAll(rotatingBots);
            for (int i = 0; i < count / 2; i++ ) {
                Partida partida=Partida.builder()
                        .liga(new LigaId(ligaId))
                        .estado(Estado.PENDANT)
                        .roundNumber(ronda)
                        .duracionTotal(liga.getMatchTime())
                        .build();
                partida=altaPartidaPort.altaPartida(partida);

                Resultado resultado=Resultado.builder()
                        .puntuacion(null)
                        .resultadoId(new ResultadoId(fixedBots.get(i).value(),partida.getPartidaId().value()))
                        .build();
                createResultadoUseCase.crearResultado(resultado);

                resultado=Resultado.builder()
                        .puntuacion(null)
                        .resultadoId(new ResultadoId(fixedBots.get(count-1-i).value(),partida.getPartidaId().value()))
                        .build();
                createResultadoUseCase.crearResultado(resultado);
            }
            Collections.rotate(rotatingBots, +1);
        }
/*
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

 */
    }
}
