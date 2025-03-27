package org.example.backend.databaseapi.application.port.in.bot;

import org.example.backend.databaseapi.domain.bot.Bot;

public interface BuscarBotPort {

    Bot buscarBot(Integer botId);
}
