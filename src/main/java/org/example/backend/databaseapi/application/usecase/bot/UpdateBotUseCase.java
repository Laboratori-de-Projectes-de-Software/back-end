package org.example.backend.databaseapi.application.usecase.bot;

import lombok.AllArgsConstructor;
import org.example.backend.databaseapi.application.port.in.bot.ActualizarBotPort;
import org.example.backend.databaseapi.application.port.out.bot.UpdateBotPort;
import org.example.backend.databaseapi.domain.Bot;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class UpdateBotUseCase implements ActualizarBotPort {

    private final UpdateBotPort updateBotPort;
    @Override
    public Bot actualizarBot(Bot changedBot, Integer id) {
        return updateBotPort.updateBot(changedBot, id);
    }
}
