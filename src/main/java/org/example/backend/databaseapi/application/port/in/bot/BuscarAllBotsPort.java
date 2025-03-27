package org.example.backend.databaseapi.application.port.in.bot;

import org.example.backend.databaseapi.domain.bot.Bot;

import java.util.List;

public interface BuscarAllBotsPort {

    List<Bot> buscarAllBots();
}
