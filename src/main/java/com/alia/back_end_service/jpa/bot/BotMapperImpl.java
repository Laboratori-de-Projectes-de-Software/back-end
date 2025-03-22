package com.alia.back_end_service.jpa.bot;

import com.alia.back_end_service.domain.bot.Bot;
import com.alia.back_end_service.jpa.league.LeagueMapper;
import com.alia.back_end_service.jpa.message.MessageMapper;
import com.alia.back_end_service.jpa.user.UserMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.stream.Collectors;

@Component
public class BotMapperImpl implements BotMapper{

    private final UserMapper userMapper;
    private final LeagueMapper leagueMapper;
    private final MessageMapper messageMapper;

    public BotMapperImpl(@Lazy UserMapper userMapper, @Lazy LeagueMapper leagueMapper, MessageMapper messageMapper) {
        this.userMapper = userMapper;
        this.leagueMapper = leagueMapper;
        this.messageMapper = messageMapper;
    }

    @Override
    public Bot toDomain(BotEntity entity) {
        return new Bot(
                entity.getName(),
                entity.getDescription(),
                entity.getEndpoint(),
                entity.getToken(),
                userMapper.toDomain(entity.getUser()),
                entity.getLeagues() != null ? entity.getLeagues().stream()
                        .map(leagueMapper::toDomain)
                        .collect(Collectors.toList()) : Collections.emptyList()
                ,
                entity.getMessages() != null ? entity.getMessages().stream()
                        .map(messageMapper::toDomain)
                        .collect(Collectors.toList()) : Collections.emptyList()
        );
    }

    @Override
    public BotEntity toEntity(Bot domain) {
        return new BotEntity(
                domain.getName(),
                domain.getDescription(),
                domain.getEndpoint(),
                domain.getToken(),
                userMapper.toEntity(domain.getUser()),
                domain.getLeagues() != null ? domain.getLeagues().stream()
                        .map(leagueMapper::toEntity)
                        .collect(Collectors.toList()) : Collections.emptyList()
                ,
                domain.getMessages() != null ? domain.getMessages().stream()
                        .map(messageMapper::toEntity)
                        .collect(Collectors.toList()) : Collections.emptyList()
        );
    }
}
