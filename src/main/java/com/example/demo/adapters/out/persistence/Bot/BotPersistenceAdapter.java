package com.example.demo.adapters.out.persistence.Bot;

import com.example.demo.adapters.out.persistence.Bot.BotEntity;
import com.example.demo.adapters.out.persistence.Bot.SpringDataBotRepository;
import com.example.demo.application.port.out.BotRepository;
import com.example.demo.domain.model.Bot;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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
    public Bot save(Bot bot) {
        // Mapeo de dominio a entidad JPA
        BotEntity entity = new BotEntity();
        entity.setId(bot.getId());
        entity.setName(bot.getName());
        entity.setDescription(bot.getDescription());
        entity.setUrlImagen(bot.getUrlImagen());
        entity.setEndpoint(bot.getEndpoint());
        entity.setUserId(bot.getUserId());
        entity.setCreatedAt(bot.getCreatedAt());
        
        BotEntity savedEntity = jpaRepository.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    public Bot findById(Integer botId) {
        BotEntity entity = jpaRepository.findById(botId)
                .orElseThrow(() -> new RuntimeException("Bot not found with id: " + botId));
        return toDomain(entity);
    }

    @Override
    public List<Bot> findAll() {
        return jpaRepository.findAll().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Bot> findByOwnerId(Integer userId) {
        return jpaRepository.findByUserId(userId).stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    private Bot toDomain(BotEntity entity) {
        Bot bot = new Bot(entity.getId(), entity.getName(), entity.getDescription(), entity.getUrlImagen(), entity.getEndpoint(), entity.getUserId());
        return bot;
    }
}
