package com.alia.back_end_service.domain.bot.port;

import com.alia.back_end_service.domain.bot.Bot;

import java.util.Optional;

public interface BotPortDB {
    Optional<Bot> findByName(String name);
    Bot save(Bot bot);
    void delete(String name);

}
