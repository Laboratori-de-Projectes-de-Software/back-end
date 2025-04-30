package org.example.backend.databaseapi.application.port.in.bot;

import org.example.backend.databaseapi.application.dto.bot.BotDTOResponse;
import org.example.backend.databaseapi.domain.bot.Bot;
import org.example.backend.databaseapi.domain.bot.BotsFilterRequest;

import java.util.List;

public interface BuscarAllBotsPort {

    List<BotDTOResponse> buscarAllBots();
    List<Bot> buscarAllBotsFiltro(BotsFilterRequest request);
}
