package org.example.backend.databaseapi.application.port.out.bot;

import org.example.backend.databaseapi.domain.Bot;

import java.util.Optional;

public interface FindBotPort {

    Optional<Bot> findBot(int id_bot);

}
