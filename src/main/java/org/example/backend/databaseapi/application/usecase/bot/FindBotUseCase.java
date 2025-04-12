package org.example.backend.databaseapi.application.usecase.bot;

import lombok.AllArgsConstructor;
import org.example.backend.databaseapi.application.dto.bot.BotDTOResponse;
import org.example.backend.databaseapi.application.exception.ResourceNotFoundException;
import org.example.backend.databaseapi.application.port.in.bot.BuscarBotPort;
import org.example.backend.databaseapi.application.port.out.bot.FindBotPort;
import org.example.backend.databaseapi.application.port.out.resultado.FindBotResultadoPort;
import org.example.backend.databaseapi.domain.bot.Bot;
import org.example.backend.databaseapi.domain.resultado.Resultado;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FindBotUseCase implements BuscarBotPort {

    private FindBotPort findBotPort;
    private FindBotResultadoPort findBotResultadoPort;

    @Override
    public BotDTOResponse buscarBot(Integer botId) {
        Bot bot=findBotPort.findBot(botId)
                .orElseThrow(()->new ResourceNotFoundException("El bot no existe!"));
        int wins=0;
        int draws=0;
        int loses=0;
        List<Resultado> resultados=findBotResultadoPort.findBotResultados(botId);
        for (Resultado resultado:resultados){
            if(resultado.getPuntuacion()==3){
                wins++;
            }else if(resultado.getPuntuacion()==0){
                loses++;
            }else{
                draws++;
            }
        }
        return new BotDTOResponse(botId, bot.getNombre(), bot.getPrompt(), bot.getImagen(), wins,draws,loses);
    }
}
