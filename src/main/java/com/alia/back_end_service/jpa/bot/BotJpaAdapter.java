package com.alia.back_end_service.jpa.bot;

import com.alia.back_end_service.domain.bot.Bot;
import com.alia.back_end_service.domain.bot.port.BotPortDB;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BotJpaAdapter implements BotPortDB {

    private final BotJpaRepository botJpaRepository;

    private final BotMapper botMapper;

    public BotJpaAdapter(BotJpaRepository botJpaRepository, BotMapper botMapper) {
        this.botJpaRepository = botJpaRepository;
        this.botMapper = botMapper;
    }

    @Override
    public Optional<Bot> findByName(String name) {
        Optional<BotEntity> botEntity = botJpaRepository.findByName(name);
        return botEntity.map(botMapper::toDomain);
    }

    @Override
    public Bot save(Bot bot) {
        BotEntity savedEntity = botJpaRepository.save(botMapper.toEntity(bot));
        return botMapper.toDomain(savedEntity);
    }

    @Override
    public void delete(String name) {
        botJpaRepository.deleteByName(name);
    }
}
