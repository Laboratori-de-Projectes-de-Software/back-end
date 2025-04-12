package org.example.backend.databaseapi.application.port.in.bot;

import org.example.backend.databaseapi.application.dto.bot.BotDTOResponse;
import org.example.backend.databaseapi.domain.bot.Bot;

public interface ActualizarBotPort {

    BotDTOResponse actualizarBot(Bot changedBot, Integer id);
}
