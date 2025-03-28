package org.example.backend.databaseapi.application.port.in.bot;

import org.example.backend.databaseapi.domain.Bot;

import java.util.List;

public interface BuscarAllUserBotsPort {

    List<Bot> buscarUserBots(Integer userId);
}
