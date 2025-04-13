package com.debateia.application.mapper;

import com.debateia.adapter.in.web.dto.request.BotDTO;
import com.debateia.adapter.in.web.dto.response.BotResponseDTO;
import com.debateia.adapter.in.web.dto.response.BotSummaryResponseDTO;
import com.debateia.adapter.out.persistence.entities.BotEntity;
import com.debateia.adapter.out.persistence.entities.UserEntity;
import com.debateia.domain.Bot;

public class BotMapper {

    public static BotSummaryResponseDTO toSummaryDTO(Bot bot) {
        BotSummaryResponseDTO dto = new BotSummaryResponseDTO();
        dto.setName(bot.getName());
        dto.setId(bot.getId());
        dto.setDescription(bot.getDescription());
        return dto;
    }

    public static Bot DTOtoDomain(BotDTO dto) {
        Bot bot = new Bot();
        bot.setDescription(dto.getDescription());
        bot.setName(dto.getName());
        bot.setEndpoint(dto.getEndpoint());
        bot.setUserId(dto.getUserId());
        bot.setUrlImage(dto.getUrlImagen());
        return bot;
    }

    public static Bot EntityToDomain(BotEntity entity) {
        Bot bot = new Bot();
        bot.setId(entity.getId());
        bot.setDraws(entity.getDraws());
        bot.setLosses(entity.getLosses());
        bot.setName(entity.getName());
        bot.setWins(entity.getWins());
        bot.setEndpoint(entity.getEndpoint());
        bot.setDescription(entity.getDescription());
        bot.setUrlImage(entity.getUrl_imagen());
        if (entity.getUser() == null) {
            bot.setUserId(null);
        } else {
            bot.setUserId(entity.getUser().getId());
        }
        return bot;
    }

    public static BotEntity toEntity(Bot dto) {
        BotEntity entity = new BotEntity();
        if (dto.getUserId() == null) {
            entity.setUser(null);
        } else {
            UserEntity dummy = new UserEntity();
            dummy.setId(dto.getUserId());
            entity.setUser(dummy);
        }
        entity.setDescription(dto.getDescription());
        entity.setName(dto.getName());
        entity.setId(dto.getId()); // id = auto increment
        entity.setUrl_imagen(dto.getUrlImage());
        entity.setEndpoint(dto.getEndpoint());
        entity.setLosses(0);
        entity.setWins(0);
        entity.setDraws(0);
        return entity;
    }

    public static BotResponseDTO toResponseDto(Bot bot) {
        BotResponseDTO dto = new BotResponseDTO();
        dto.setBotId(bot.getId());
        dto.setName(bot.getName());
        dto.setDescription(bot.getDescription());
        dto.setUrlImage(bot.getUrlImage());
        dto.setNLosses(bot.getLosses());
        dto.setNWins(bot.getWins());
        dto.setNDraws(bot.getDraws());
        return dto;
    }
}
