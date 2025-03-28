package org.example.backend.databaseapi.application.usecase.bot;

import lombok.AllArgsConstructor;
import org.example.backend.databaseapi.application.exception.ResourceNotFoundException;
import org.example.backend.databaseapi.application.port.in.bot.BuscarBotPort;
import org.example.backend.databaseapi.application.port.out.bot.FindBotPort;
import org.example.backend.databaseapi.domain.Bot;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class FindBotUseCase implements BuscarBotPort {
    private FindBotPort findBotPort;

    @Override
    public Bot buscarBot(Integer botId) {
        return findBotPort.findBot(botId)
                .orElseThrow(()->new ResourceNotFoundException("El bot no existe!"));
    }
}
