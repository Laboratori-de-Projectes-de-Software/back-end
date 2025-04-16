package com.example.gironetaServer.infraestructure.adapters.in.controllers.mappers;

import com.example.gironetaServer.application.ports.UserRepository;
import com.example.gironetaServer.domain.Bot;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.dto.BotDto;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.dto.BotResponseDTO;
import com.example.gironetaServer.infraestructure.adapters.out.db.entities.BotEntity;
import com.example.gironetaServer.infraestructure.adapters.out.db.entities.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class BotMapper {

    private final UserRepository userRepository;

    public BotMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Bot toDomain(BotEntity botEntity) {
        Bot bot = new Bot();
        bot.setId(botEntity.getId());
        bot.setName(botEntity.getName());
        bot.setQuality(botEntity.getQuality());
        bot.setImageUrl(botEntity.getImageUrl());
        bot.setApiUrl(botEntity.getApiUrl());
        bot.setUsuario_id(botEntity.getUsuario().getId());
        return bot;
    }

    public BotEntity toEntity(Bot bot) {
        BotEntity botEntity = new BotEntity();
        botEntity.setId(bot.getId());
        botEntity.setName(bot.getName());
        botEntity.setQuality(bot.getQuality());
        botEntity.setImageUrl(bot.getImageUrl());
        botEntity.setApiUrl(bot.getApiUrl());
        UserEntity userEntity = UserMapper.toEntity(userRepository.getUserById(bot.getUsuario_id()));
        botEntity.setUsuario(userEntity);
        return botEntity;
    }

    public static BotResponseDTO toBotResponseDto(Bot bot) {
        BotResponseDTO toBotResponseDto = new BotResponseDTO();
        toBotResponseDto.setId(bot.getId());
        toBotResponseDto.setName(bot.getName());
        toBotResponseDto.setQuality(bot.getQuality());
        toBotResponseDto.setImageUrl(bot.getImageUrl());
        toBotResponseDto.setApiUrl(bot.getApiUrl());
        toBotResponseDto.setnWins(bot.getnWins());
        toBotResponseDto.setnLosses(bot.getnLosses());
        toBotResponseDto.setnDraws(bot.getnDraws());
        return toBotResponseDto;
    }

    public static Bot toAppObject(BotDto botDto) {
        Bot bot = new Bot();
        bot.setName(botDto.getName());
        bot.setQuality(botDto.getQuality());
        bot.setImageUrl(botDto.getImageUrl());
        bot.setApiUrl(botDto.getApiUrl());
        return bot;
    }
}