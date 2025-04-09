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
    public List<Bot> getAllUserBots(String username) {

        List<BotEntity> botEntities = botJpaRepository.getAllByUser_Username(username);
        return  botEntities.stream().map(botMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public Bot findById(Integer id){
        return botMapper.toDomain(botJpaRepository.findById(id).orElse(null));
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
        UserEntity userEntity = userJpaRepository.findByUsername(bot.getUserId())
                .orElseThrow(UserNotFoundException::new);

        botEntity.setUser(userEntity);

        BotEntity savedEntity = botJpaRepository.save(botEntity);
        return botMapper.toDomain(savedEntity);
    }

    @Override
    public void update(Bot bot, Integer id) {
        BotEntity getBotEntity = botJpaRepository.findById(id).orElseThrow(UserNotFoundException::new);
        getBotEntity.setName(bot.getName()==null ? getBotEntity.getName():bot.getName());
        getBotEntity.setDescription(bot.getDescription()==null? getBotEntity.getDescription():bot.getDescription());
        getBotEntity.setEndpoint(bot.getEndpoint()==null? getBotEntity.getEndpoint():bot.getEndpoint().toString());
        botJpaRepository.save(getBotEntity);
    }

    @Override
    public void delete(String name) {
        botJpaRepository.deleteByName(name);
    }

    @Override
    public Optional<Bot> findByEndpoint(String endpoint) {
        Optional<BotEntity> botEntity = botJpaRepository.findByEndpoint(endpoint);
        return botEntity.map(botMapper::toDomain);
    }
}
