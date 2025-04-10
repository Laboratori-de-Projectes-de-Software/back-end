package com.alia.back_end_service.jpa.user;

import com.alia.back_end_service.domain.user.User;
import com.alia.back_end_service.jpa.bot.BotEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toDomain(UserEntity entity) {
        User user = new User();
        user.setId(entity.getId());
        user.setUsername(entity.getUsername());
        user.setMail(entity.getMail());
        user.setPassword(entity.getPassword());
        return user;
    }

    @Override
    public UserEntity toEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setUsername(user.getUsername());
        entity.setMail(user.getMail());
        entity.setPassword(user.getPassword());
        return entity;
    }
}
