package com.debateia.adapter.out.persistence;

import com.debateia.application.ports.out.persistence.AIRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class JpaAIRepository implements AIRepository {
    private final AIJpaRepository aiJpaRepository;

    @Override
    public List<AIEntity> findAll() {
        return aiJpaRepository.findAll();
    }

    @Override
    public List<AIEntity> findByUserId(Integer userId) {
        return aiJpaRepository.findByUser_Id(userId);
    }

    public AIEntity save(AIEntity entity) {
        return aiJpaRepository.save(entity);
    }
}
