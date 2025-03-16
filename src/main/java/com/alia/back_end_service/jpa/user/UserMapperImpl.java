package com.alia.back_end_service.jpa.user;

import com.alia.back_end_service.domain.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper{

    @Override
    public User toDomain(UserEntity entity) {
        return new User(entity.getUsername(),entity.getMail(),entity.getPassword(),entity.getPhoto(), entity.getRole());
    }

    @Override
    public UserEntity toEntity(User user) {
        return new UserEntity(user.getUsername(), user.getMail(), user.getPassword(), user.getPhoto(), user.getRole());
    }
}
