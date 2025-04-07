package org.example.backend.databaseapi.application.usecase.liga;

import lombok.AllArgsConstructor;
import org.example.backend.databaseapi.application.dto.resultado.ParticipationDTOResponse;
import org.example.backend.databaseapi.application.port.in.liga.ClasificacionLigaPort;
import org.example.backend.databaseapi.application.port.out.bot.FindBotPort;
import org.example.backend.databaseapi.application.port.out.resultado.FindLigaResultadoPort;
import org.example.backend.databaseapi.domain.bot.Bot;
import org.example.backend.databaseapi.domain.resultado.Resultado;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

@Component
@AllArgsConstructor
public class GetClasificacionesLigaUseCase implements ClasificacionLigaPort {

    private final FindLigaResultadoPort findLigaResultadoPort;
    private final FindBotPort findBotPort;

    @Override
    public List<ParticipationDTOResponse> getClasificacionesLiga(Integer ligaId) {
        List<Resultado> resultados=findLigaResultadoPort.findResultadoLiga(ligaId);
        HashMap<Integer,Integer> botPuntuacion=new HashMap<>();
        for(Resultado resultado:resultados){
            botPuntuacion.put(
                    resultado.getResultadoId().botvalue(),
                    botPuntuacion.getOrDefault(resultado.getResultadoId().botvalue(),0)+resultado.getPuntuacion()
            );
        }
        List<ParticipationDTOResponse> participations= botPuntuacion.entrySet()
                .stream()
                .map( entry-> new ParticipationDTOResponse(
                        entry.getKey(),
                        findBotPort.findBot(entry.getKey())
                                .orElseThrow()
                                .getNombre(),
                        entry.getValue(),
                        0
                        )
                )
                .sorted(Comparator.comparing(ParticipationDTOResponse::getPoints))
                .toList();

        int order=0;
        for(ParticipationDTOResponse response:participations){
            response.setPosition(++order);
        }

        return participations;
    }
}
