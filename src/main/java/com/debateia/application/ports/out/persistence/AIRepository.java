package com.debateia.application.ports.out.persistence;


import com.debateia.adapter.out.persistence.AIEntity;

import java.util.List;

public interface AIRepository {
    List<AIEntity> findAll();
    List<AIEntity> findByUserId(Integer userId);
    AIEntity save(AIEntity entity);
}
