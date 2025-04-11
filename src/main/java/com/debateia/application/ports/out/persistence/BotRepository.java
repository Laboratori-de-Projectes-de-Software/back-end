package com.debateia.application.ports.out.persistence;


import com.debateia.adapter.out.persistence.entities.BotEntity;

import java.util.List;
import java.util.Optional;

public interface BotRepository {
    List<BotEntity> findAll();
    List<BotEntity> findByUserId(Integer userId);
    BotEntity save(BotEntity entity);
    Optional<BotEntity> findById(Integer botId);
}
