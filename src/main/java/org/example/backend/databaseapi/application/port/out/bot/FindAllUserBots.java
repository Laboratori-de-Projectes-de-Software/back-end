package org.example.backend.databaseapi.application.port.out.bot;

import org.example.backend.databaseapi.domain.bot.Bot;

import java.util.List;

public interface FindAllUserBots {
    List<Bot> findAllBots(int id_user);
}
