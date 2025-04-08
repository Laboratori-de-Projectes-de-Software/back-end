package org.example.backend.databaseapi.application.port.in.bot;

import org.example.backend.databaseapi.application.dto.bot.BotDTOResponse;
import org.example.backend.databaseapi.domain.bot.Bot;

import java.util.List;

public interface BuscarAllUserBotsPort {

    List<BotDTOResponse> buscarUserBots(Integer userId);
}
