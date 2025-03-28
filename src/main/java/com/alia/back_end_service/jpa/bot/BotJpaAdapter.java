package com.alia.back_end_service.jpa.bot;

import com.alia.back_end_service.domain.bot.Bot;
import com.alia.back_end_service.domain.bot.port.BotPortDB;
import com.alia.back_end_service.domain.user.exceptions.UserNotFoundException;
import com.alia.back_end_service.jpa.user.UserEntity;
import com.alia.back_end_service.jpa.user.UserJpaRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class BotJpaAdapter implements BotPortDB {

    private final BotJpaRepository botJpaRepository;

    private final BotMapper botMapper;

    private final UserJpaRepository userJpaRepository;

    public BotJpaAdapter(BotJpaRepository botJpaRepository, BotMapper botMapper, UserJpaRepository userJpaRepository) {
        this.botJpaRepository = botJpaRepository;
        this.botMapper = botMapper;
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public List<Bot> getBots() {
        List<BotEntity> botEntities = botJpaRepository.findAll();
        return botEntities.stream()
                .map(botMapper::toDomain) // Aplicamos la función de mapeo
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Bot> findByName(String name) {
        Optional<BotEntity> botEntity = botJpaRepository.findByName(name);
        return botEntity.map(botMapper::toDomain);
    }

    @Override
    public Bot save(Bot bot) {
        BotEntity botEntity = botMapper.toEntity(bot);

        // Resolver relación con usuario
        UserEntity userEntity = userJpaRepository.findById(bot.getUserId())
                .orElseThrow(UserNotFoundException::new);

        botEntity.setUser(userEntity);

        BotEntity savedEntity = botJpaRepository.save(botEntity);
        return botMapper.toDomain(savedEntity);
    }



    @Override
    public void delete(String name) {
        botJpaRepository.deleteByName(name);
    }
}
