package com.example.demo.adapters.out.persistence.Bot;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.demo.application.port.out.BotRepository;
import com.example.demo.domain.model.Bot;

/**
 * Adaptador que implementa el puerto de salida usando Spring Data JPA.
 */
@Component
public class BotPersistenceAdapter implements BotRepository {

    private final SpringDataBotRepository jpaRepository;

    public BotPersistenceAdapter(SpringDataBotRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Bot save(Bot Bot) {
        // Mapeo de dominio a entidad JPA
        BotEntity entity = new BotEntity();
        entity.setId(Bot.getId()); // Será null si es nuevo
        entity.setName(Bot.getName());
        entity.setCreatedAt(Bot.getCreatedAt());
        
        BotEntity savedEntity = jpaRepository.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    public Bot findById(Long BotId) {
        BotEntity entity = jpaRepository.findById(BotId)
                .orElseThrow(() -> new RuntimeException("Bot not found with id: " + BotId));
        return toDomain(entity);
    }

    @Override
    public List<Bot> findAll() {
        return jpaRepository.findAll().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    private Bot toDomain(BotEntity entity) {
        Bot Bot = new Bot(entity.getName());
        Bot.setId(entity.getId());
        Bot.setCreatedAt(entity.getCreatedAt());
        return Bot;
    }
}
