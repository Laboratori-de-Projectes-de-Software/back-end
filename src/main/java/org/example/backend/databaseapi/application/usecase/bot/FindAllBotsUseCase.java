package org.example.backend.databaseapi.application.usecase.bot;

import lombok.AllArgsConstructor;
import org.example.backend.databaseapi.application.dto.bot.BotDTOResponse;
import org.example.backend.databaseapi.application.port.in.bot.BuscarAllBotsPort;
import org.example.backend.databaseapi.application.port.out.bot.FindAllBotsPort;
import org.example.backend.databaseapi.application.port.out.resultado.FindBotResultadoPort;
import org.example.backend.databaseapi.domain.bot.Bot;
import org.example.backend.databaseapi.domain.bot.BotsFilterRequest;
import org.example.backend.databaseapi.domain.resultado.Resultado;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class FindAllBotsUseCase implements BuscarAllBotsPort {

    private final FindAllBotsPort findAllBotsPort;
    private final FindBotResultadoPort findBotResultadoPort;

    @Override
    public List<BotDTOResponse> buscarAllBots() {
        return findAllBotsPort.findAllBots()
                .stream()
                .map(bot -> {
                    int wins=0;
                    int draws=0;
                    int loses=0;
                    List<Resultado> resultados=findBotResultadoPort.findBotResultados(bot.getIdBot().value());
                    for (Resultado resultado:resultados){
                        if(resultado.getPuntuacion()==3){
                            wins++;
                        }else if(resultado.getPuntuacion()==0){
                            loses++;
                        }else{
                            draws++;
                        }
                    }
                    return new BotDTOResponse(bot.getIdBot().value(), bot.getNombre(), bot.getPrompt(), bot.getImagen(), wins,draws,loses);
                })
                .toList();
    }

    @Override
    public List<Bot> buscarAllBotsFiltro(BotsFilterRequest request) {
        return findAllBotsPort.findAllBotsFilter(request);
    }
}
