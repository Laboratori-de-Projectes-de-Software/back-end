package com.alia.back_end_service.domain.bot.port;

import com.alia.back_end_service.domain.bot.Bot;

import java.util.List;
import java.util.Optional;

public interface BotPortDB {
    List<Bot> getBots();
    List<Bot> getAllUserBots(String username);
    Optional<Bot> findByName(String name);
    Bot findById(Integer id);

    Bot save(Bot bot);
    void update(Bot bot, Integer id);
    void delete(String name);
    Optional<Bot> findByEndpoint(String endpoint);
}
