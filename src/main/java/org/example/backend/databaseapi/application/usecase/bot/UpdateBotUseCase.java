package org.example.backend.databaseapi.application.usecase.bot;

import lombok.AllArgsConstructor;
import org.example.backend.databaseapi.application.dto.bot.BotDTOResponse;
import org.example.backend.databaseapi.application.port.in.bot.ActualizarBotPort;
import org.example.backend.databaseapi.application.port.out.bot.UpdateBotPort;
import org.example.backend.databaseapi.application.port.out.resultado.FindBotResultadoPort;
import org.example.backend.databaseapi.domain.bot.Bot;
import org.example.backend.databaseapi.domain.resultado.Resultado;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UpdateBotUseCase implements ActualizarBotPort {

    private final UpdateBotPort updateBotPort;
    private final FindBotResultadoPort findBotResultadoPort;
    
    @Override
    public BotDTOResponse actualizarBot(Bot changedBot, Integer id) {
        Bot bot=updateBotPort.updateBot(changedBot, id);
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
    }
}
