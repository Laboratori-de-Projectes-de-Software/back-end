package com.alia.back_end_service.jpa.bot;

import com.alia.back_end_service.domain.bot.Bot;
import com.alia.back_end_service.jpa.user.UserMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class BotMapperImpl implements BotMapper{

    private final UserMapper userMapper;

    public BotMapperImpl(@Lazy UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public Bot toDomain(BotEntity entity) {
        return new Bot(
                entity.getName(),
                entity.getDescription(),
                entity.getEndpoint(),
                entity.getToken(),
                userMapper.toDomain(entity.getUser())
        );
    }

    @Override
    public BotEntity toEntity(Bot domain) {
        return new BotEntity(
                domain.getName(),
                domain.getDescription(),
                domain.getEndpoint(),
                domain.getToken(),
                userMapper.toEntity(domain.getUser())
        );
    }
}
