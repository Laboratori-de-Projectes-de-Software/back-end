package com.debateia.adapter.out.bot;

import com.debateia.adapter.out.bot.BotJpaRepository;
import com.debateia.adapter.mapper.BotMapper;
import com.debateia.application.ports.out.persistence.BotRepository;
import com.debateia.domain.Bot;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BotRepo implements BotRepository {
    private final BotJpaRepository botJpaRepository;
    private final BotMapper botMapper;

    @Override
    public List<Bot> findAll() {
        List<BotEntity> entities = botJpaRepository.findAll();
        return entities.stream().map(botMapper::EntityToDomain).toList();
    }

    @Override
    public List<Bot> findByUserId(Integer userId) {
        List<BotEntity> entities = botJpaRepository.findByUser_Id(userId);
        return entities.stream().map(botMapper::EntityToDomain).toList();
    }

    @Override
    public Bot save(Bot bot) {
        BotEntity saved = botJpaRepository.save(botMapper.toEntity(bot));
        return botMapper.EntityToDomain(saved);
    }
    @Override
    public Optional<Bot> findById(Integer botId) {
        return botJpaRepository.findById(botId)
                .map(botMapper::EntityToDomain);
    }
    @Override
    public boolean exists(String name) {
        return botJpaRepository.existsByName(name);
    }
}
