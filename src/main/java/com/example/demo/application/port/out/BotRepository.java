package com.example.demo.application.port.out;

import com.example.demo.domain.model.Bot;
import com.example.demo.domain.model.League;

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
