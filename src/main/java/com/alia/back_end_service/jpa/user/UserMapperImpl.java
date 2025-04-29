package com.alia.back_end_service.jpa.user;

import com.alia.back_end_service.domain.user.User;
import com.alia.back_end_service.jpa.bot.BotEntity;
import com.alia.back_end_service.jpa.bot.BotMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toDomain(UserEntity entity) {
        List<String> botIds = entity.getBots() != null
                ? entity.getBots().stream().map(BotEntity::getName).toList()
                : List.of();

        return new User(
                entity.getUsername(),
                entity.getMail(),
                entity.getPassword(),
                entity.getRole(),
                botIds
        );
    }

    @Override
    public UserEntity toEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setUsername(user.getUsername());
        entity.setMail(user.getMail());
        entity.setPassword(user.getPassword());
        entity.setRole(user.getRole());
        // Los bots se asignan desde fuera!!!!
        return entity;
    }
}
