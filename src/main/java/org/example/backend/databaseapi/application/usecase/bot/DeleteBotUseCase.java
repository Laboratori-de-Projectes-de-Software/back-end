package org.example.backend.databaseapi.application.usecase.bot;

import lombok.AllArgsConstructor;
import org.example.backend.databaseapi.application.port.in.bot.EliminarBotPort;
import org.example.backend.databaseapi.application.port.out.bot.DeleteBotPort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteBotUseCase implements EliminarBotPort {

    private final DeleteBotPort deleteBotPort;

    @Override
    public void eliminarBot(Integer botId) {
        deleteBotPort.deleteBot(botId);
    }
}
