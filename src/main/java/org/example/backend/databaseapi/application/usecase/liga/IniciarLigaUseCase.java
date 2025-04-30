package org.example.backend.databaseapi.application.usecase.liga;

import lombok.AllArgsConstructor;
import org.example.backend.databaseapi.application.port.in.liga.IniciarLigaPort;
import org.example.backend.databaseapi.application.port.in.partida.AltaPartidaPort;
import org.example.backend.databaseapi.application.port.out.liga.FindLigaPort;
import org.example.backend.databaseapi.application.port.out.liga.UpdateLigaPort;
import org.example.backend.databaseapi.application.port.out.partida.CreatePartidaPort;
import org.example.backend.databaseapi.application.usecase.partida.CreatePartidaUseCase;
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

    private FindLigaUseCase findLigaUseCase;
    private CreatePartidaUseCase createPartidaUseCase;
    private ActualizarLigaUseCase actualizarLigaUseCase;
    private CreateResultadoUseCase createResultadoUseCase;

    @Override
    public void iniciarLiga(Integer ligaId) {
        Liga liga=findLigaUseCase.buscarLiga(ligaId);

        List<BotId> rotatingBots=liga.getBotsLiga().subList(1,liga.getBotsLiga().size());
        int count=liga.getBotsLiga().size();

        //Se añade un bot que nos indica que no tiene emparejamiento
        if(count%2==1){
            rotatingBots.add(new BotId(-1));
            count++;
        }
        for (int ronda=1;ronda<count;ronda++){
            List<BotId> fixedBots = new ArrayList<>();
            fixedBots.add(liga.getBotsLiga().getFirst());
            fixedBots.addAll(rotatingBots);
            for (int i = 0; i < count / 2; i++ ) {
                int botid1=fixedBots.get(i).value();
                int botid2=fixedBots.get(count-1-i).value();

                //Si el bot tiene id -1 significa que no tiene emparejamiento
                if(botid1==-1 || botid2==-1){
                    continue;
                }

                Partida partida=Partida.builder()
                        .liga(new LigaId(ligaId))
                        .roundNumber(ronda)
                        .duracionTotal(liga.getMatchTime())
                        .build();

                //Tal vez se tenga que modificar en un futuro
                if(ronda==1){
                    partida.setEstado(Estado.IN_PROCESS);
                }else{
                    partida.setEstado(Estado.PENDANT);
                }

                partida=createPartidaUseCase.altaPartida(partida);

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

        for(BotId id:rotatingBots){
            if(id.value()==-1){
                rotatingBots.remove(id);
                break;
            }
        }
        liga.setEstado(Estado.IN_PROCESS);
        actualizarLigaUseCase.actualizarLiga(liga,ligaId);
    }
}
