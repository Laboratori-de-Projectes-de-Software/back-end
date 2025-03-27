package org.example.backend.databaseapi.application.usecase.bot;

import lombok.AllArgsConstructor;
import org.example.backend.databaseapi.application.port.in.bot.BuscarAllUserBotsPort;
import org.example.backend.databaseapi.application.port.out.bot.FindAllUserBots;
import org.example.backend.databaseapi.domain.bot.Bot;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class FindAllUserBotsUseCase implements BuscarAllUserBotsPort {
    private FindAllUserBots findBotPort;

    @Override
    public List<Bot> buscarUserBots(Integer userId) {
        return findBotPort.findAllBots(userId);
    }
}
