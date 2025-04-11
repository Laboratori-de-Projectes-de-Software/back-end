package com.debateia.adapter.out.persistence;

import com.debateia.adapter.out.persistence.entities.BotEntity;
import com.debateia.application.ports.out.persistence.BotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JpaBotRepository implements BotRepository {
    private final BotJpaRepository botJpaRepository;

    @Override
    public List<BotEntity> findAll() {
        return botJpaRepository.findAll();
    }

    @Override
    public List<BotEntity> findByUserId(Integer userId) {
        return botJpaRepository.findByUser_Id(userId);
    }
    public BotEntity save(BotEntity entity) {
        return botJpaRepository.save(entity);
    }
    public Optional<BotEntity> findById(Integer botId) {
        return botJpaRepository.findById(botId);
    }
    public boolean exists(String name) {
        return botJpaRepository.existsByName(name);
    }
}
