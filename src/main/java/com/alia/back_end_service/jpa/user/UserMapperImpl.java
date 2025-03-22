package com.alia.back_end_service.jpa.user;

import com.alia.back_end_service.domain.user.User;
import com.alia.back_end_service.jpa.bot.BotMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.stream.Collectors;

@Component
public class UserMapperImpl implements UserMapper{

    private final BotMapper botMapper;

    public UserMapperImpl(@Lazy BotMapper botMapper) {
        this.botMapper = botMapper;
    }

    @Override
    public User toDomain(UserEntity entity) {
        return new User(
                entity.getUsername(),
                entity.getMail(),
                entity.getPassword(),
                entity.getPhoto(),
                entity.getRole(),
                entity.getBots() != null ? entity.getBots().stream()
                        .map(botMapper::toDomain)
                        .collect(Collectors.toList()) : Collections.emptyList()
        );
    }

    @Override
    public UserEntity toEntity(User user) {
        return new UserEntity(
                user.getUsername(),
                user.getMail(),
                user.getPassword(),
                user.getPhoto(),
                user.getRole(),
                user.getBots() != null ? user.getBots().stream().map(botMapper::toEntity).collect(Collectors.toList())
                : Collections.emptyList()
        );
    }
}
