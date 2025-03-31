package org.example.backend.databaseapi.application.usecase.bot;

import lombok.AllArgsConstructor;
import org.example.backend.databaseapi.application.port.in.bot.BuscarAllBotsPort;
import org.example.backend.databaseapi.application.port.out.bot.FindAllBotsPort;
import org.example.backend.databaseapi.domain.bot.Bot;
import org.example.backend.databaseapi.domain.bot.BotsFilterRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class FindAllBotsUseCase implements BuscarAllBotsPort {

    private final FindAllBotsPort findAllBotsPort;

    @Override
    public List<Bot> buscarAllBots() {
        return findAllBotsPort.findAllBots();
    }

    @Override
    public List<Bot> buscarAllBotsFiltro(BotsFilterRequest request) {
        return findAllBotsPort.findAllBotsFilter(request);
    }
}
