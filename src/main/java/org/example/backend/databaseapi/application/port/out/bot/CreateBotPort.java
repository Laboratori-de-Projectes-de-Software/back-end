package org.example.backend.databaseapi.application.port.out.bot;

import org.example.backend.databaseapi.domain.Bot;

import java.util.Optional;


public interface CreateBotPort {

    Optional<Bot> createBot(Bot bot);

}
