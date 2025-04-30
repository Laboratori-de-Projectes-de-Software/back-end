package org.example.backend.databaseapi.application.usecase.liga;

import lombok.AllArgsConstructor;
import org.example.backend.databaseapi.application.dto.resultado.ParticipationDTOResponse;
import org.example.backend.databaseapi.application.port.in.liga.ClasificacionLigaPort;
import org.example.backend.databaseapi.application.port.out.bot.FindBotPort;
import org.example.backend.databaseapi.application.port.out.liga.FindLigaPort;
import org.example.backend.databaseapi.application.port.out.resultado.FindBotResultadoPort;
import org.example.backend.databaseapi.application.port.out.resultado.FindLigaResultadoPort;
import org.example.backend.databaseapi.domain.bot.Bot;
import org.example.backend.databaseapi.domain.bot.BotId;
import org.example.backend.databaseapi.domain.liga.Liga;
import org.example.backend.databaseapi.domain.resultado.Resultado;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

@Service
@AllArgsConstructor
public class GetClasificacionesLigaUseCase implements ClasificacionLigaPort {

    private final FindLigaResultadoPort findLigaResultadoPort;
    private final FindBotPort findBotPort;
    private final FindLigaPort findLigaPort;
    private final FindBotResultadoPort findBotResultadoPort;

    @Override
    public List<ParticipationDTOResponse> getClasificacionesLiga(Integer ligaId) {

        //Obtener bots de la liga
        Liga liga=findLigaPort.findLiga(ligaId)
                .orElseThrow();
        List<BotId> bots=liga.getBotsLiga();

        //Inicializar a 0
        HashMap<Integer,Integer> botPuntuacion=new HashMap<>();
        HashMap<Integer,Integer> botWins=new HashMap<>();
        HashMap<Integer,Integer> botLoses=new HashMap<>();
        HashMap<Integer,Integer> botDraws=new HashMap<>();
        for(BotId bot:bots){
            botPuntuacion.put(bot.value(),0);
            botWins.put(bot.value(),0);
            botLoses.put(bot.value(),0);
            botDraws.put(bot.value(),0);
        }



        //Encontrar los todos los resultados de la liga
        List<Resultado> resultados=findLigaResultadoPort.findResultadoLiga(ligaId);
        for(Resultado resultado:resultados){
            int puntos=0;
            if(resultado.getPuntuacion()!=null){
                puntos=resultado.getPuntuacion();
                if(puntos==3){
                    botWins.put(resultado.getResultadoId().botvalue(),
                            botWins.get(resultado.getResultadoId().botvalue())+1
                            );
                }else if(puntos==0){
                    botLoses.put(resultado.getResultadoId().botvalue(),
                            botLoses.get(resultado.getResultadoId().botvalue())+1
                    );
                }else{
                    botDraws.put(resultado.getResultadoId().botvalue(),
                            botDraws.get(resultado.getResultadoId().botvalue())+1
                    );
                }
            }
            botPuntuacion.put(
                    resultado.getResultadoId().botvalue(),
                    botPuntuacion.get(resultado.getResultadoId().botvalue())+puntos
            );
        }

        List<ParticipationDTOResponse> participations= botPuntuacion.entrySet()
                .stream()
                .map( entry->
                        new ParticipationDTOResponse(
                            entry.getKey(),
                            findBotPort.findBot(entry.getKey())
                                    .orElseThrow()
                                    .getNombre(),
                            entry.getValue(),
                            0,
                            botWins.get(entry.getKey()),
                            botLoses.get(entry.getKey()),
                            botDraws.get(entry.getKey())
                            )
                )
                .sorted(Comparator.comparing(ParticipationDTOResponse::getPoints).reversed())
                .toList();
        int order=0;
        for(ParticipationDTOResponse response:participations){
            response.setPosition(++order);
        }
        return participations;
    }
}
