package org.example.backend.databaseapi.application.port.out.partida;

import org.example.backend.databaseapi.domain.bot.Bot;

import java.util.List;

public interface FindBotsPartidaPort {

    List<Bot> findBotsPartida(Integer partidaId);
}
