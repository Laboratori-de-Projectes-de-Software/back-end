package com.debateia.application.ports.out.persistence;


import com.debateia.domain.Bot;

import java.util.List;
import java.util.Optional;

public interface BotRepository {
    List<Bot> findAll();
    List<Bot> findByUserId(Integer userId);
    Bot save(Bot bot);
    boolean exists(String name);
    Optional<Bot> findById(Integer botId);
}
