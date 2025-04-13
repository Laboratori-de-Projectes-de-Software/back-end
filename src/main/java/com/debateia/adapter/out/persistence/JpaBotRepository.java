package com.debateia.adapter.out.persistence;

import com.debateia.adapter.out.persistence.entities.BotEntity;
import com.debateia.application.mapper.BotMapper;
import com.debateia.application.ports.out.persistence.BotRepository;
import com.debateia.domain.Bot;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JpaBotRepository implements BotRepository {
    private final BotJpaRepository botJpaRepository;

    @Override
    public List<Bot> findAll() {
        List<BotEntity> entities = botJpaRepository.findAll();
        return entities.stream().map(BotMapper::EntityToDomain).toList();
    }

    @Override
    public List<Bot> findByUserId(Integer userId) {
        List<BotEntity> entities = botJpaRepository.findByUser_Id(userId);
        return entities.stream().map(BotMapper::EntityToDomain).toList();
    }

    @Override
    public Bot save(Bot bot) {
        BotEntity saved = botJpaRepository.save(BotMapper.toEntity(bot));
        return BotMapper.EntityToDomain(saved);
    }
    @Override
    public Optional<Bot> findById(Integer botId) {
        return botJpaRepository.findById(botId)
                .map(BotMapper::EntityToDomain);
    }
    @Override
    public boolean exists(String name) {
        return botJpaRepository.existsByName(name);
    }
}
