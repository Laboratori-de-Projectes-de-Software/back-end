package com.example.gironetaServer.infraestructure.adapters.in.controllers.mappers;

import com.example.gironetaServer.application.ports.UserRepository;
import com.example.gironetaServer.domain.Bot;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.dto.BotDto;
import com.example.gironetaServer.infraestructure.adapters.out.db.entities.BotEntity;
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
        bot.setDescripcion(botEntity.getDescripcion());
        bot.setUrlImagen(botEntity.getUrlImagen());
        bot.setEndpoint(botEntity.getEndpoint());
        bot.setUsuario_correo(botEntity.getUsuario().getEmail());
        return bot;
    }

    public BotEntity toEntity(Bot bot) {
        BotEntity botEntity = new BotEntity();
        botEntity.setId(bot.getId());
        botEntity.setName(bot.getName());
        botEntity.setDescripcion(bot.getDescripcion());
        botEntity.setUrlImagen(bot.getUrlImagen());
        botEntity.setEndpoint(bot.getEndpoint());
        botEntity.setUsuario(UserMapper.toEntity(userRepository.getUserByEmail(bot.getUsuario_correo())));
        return botEntity;
    }

    public static BotDto toBotDto(Bot bot) {
        BotDto botDto = new BotDto();
        botDto.setId(bot.getId());
        botDto.setName(bot.getName());
        botDto.setDescripcion(bot.getDescripcion());
        botDto.setUrlImagen(bot.getUrlImagen());
        botDto.setEndpoint(bot.getEndpoint());
        botDto.setUsuario_correo(bot.getUsuario_correo());
        return botDto;
    }

    public static Bot toAppObject(BotDto botDto) {
        Bot bot = new Bot();
        bot.setId(botDto.getId());
        bot.setName(botDto.getName());
        bot.setDescripcion(botDto.getDescripcion());
        bot.setUrlImagen(botDto.getUrlImagen());
        bot.setEndpoint(botDto.getEndpoint());
        bot.setUsuario_correo(botDto.getUsuario_correo());
        return bot;
    }
}