package org.example.backend.databaseapi.application.port.in.bot;

import org.example.backend.databaseapi.domain.Bot;

public interface ActualizarBotPort {

    Bot actualizarBot(Bot changedBot, Integer id);
}
