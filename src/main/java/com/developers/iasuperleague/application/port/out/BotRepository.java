package com.developers.iasuperleague.application.port.out;

import com.developers.iasuperleague.domain.model.Bot;

import java.util.List;

/**
 * Define las operaciones para guardar y obtener ligas.
 */
public interface BotRepository {
    Bot save(Bot bot);
    Bot findById(Integer botId);
    List<Bot> findAll();
    List<Bot> findByOwnerId(Integer botId);
}
