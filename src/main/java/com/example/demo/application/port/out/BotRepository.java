package com.example.demo.application.port.out;

import java.util.List;

import com.example.demo.domain.model.Bot;

/**
 * Define las operaciones para guardar y obtener ligas.
 */
public interface BotRepository {
    Bot save(Bot Bot);
    Bot findById(Long BotId);
    List<Bot> findAll();
}
