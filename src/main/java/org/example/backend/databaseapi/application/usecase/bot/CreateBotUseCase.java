package org.example.backend.databaseapi.application.usecase.bot;

import lombok.AllArgsConstructor;
import org.example.backend.databaseapi.application.exception.ResourceAlreadyExistsException;
import org.example.backend.databaseapi.application.port.in.bot.AltaBotPort;
import org.example.backend.databaseapi.application.port.out.bot.CreateBotPort;
import org.example.backend.databaseapi.domain.bot.Bot;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateBotUseCase implements AltaBotPort {

    private final CreateBotPort createBotPort;

    @Override
    public Bot altaBot(Bot bot) {
        return createBotPort.createBot(bot)
                .orElseThrow(()->new ResourceAlreadyExistsException("Ya existe un bot con ese nombre o esa URL"));
    }
}
