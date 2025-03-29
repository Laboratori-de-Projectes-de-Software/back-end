package com.alia.back_end_service.jpa.bot;

import com.alia.back_end_service.domain.bot.Bot;
import com.alia.back_end_service.jpa.league.LeagueEntity;
import com.alia.back_end_service.jpa.league.LeagueMapper;
import com.alia.back_end_service.jpa.message.MessageMapper;
import com.alia.back_end_service.jpa.user.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BotMapperImpl implements BotMapper{

    @Override
    public Bot toDomain(BotEntity entity) {
        return new Bot(
                entity.getName(),
                entity.getDescription(),
                URI.create(entity.getEndpoint()),
                entity.getToken(),
                entity.getUser().getUsername()
        );
    }

    @Override
    public BotEntity toEntity(Bot bot) {
        BotEntity entity = new BotEntity();
        entity.setName(bot.getName());
        entity.setDescription(bot.getDescription());
        entity.setEndpoint(bot.getEndpoint().toString());
        entity.setToken(bot.getToken());
        return entity;
    }
}
