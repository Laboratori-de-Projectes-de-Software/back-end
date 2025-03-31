package org.example.backend.databaseapi.application.port.in.bot;

import org.example.backend.databaseapi.domain.bot.Bot;
import org.example.backend.databaseapi.domain.bot.BotsFilterRequest;

import java.util.List;

public interface BuscarAllBotsPort {

    List<Bot> buscarAllBots();
    List<Bot> buscarAllBotsFiltro(BotsFilterRequest request);
}
