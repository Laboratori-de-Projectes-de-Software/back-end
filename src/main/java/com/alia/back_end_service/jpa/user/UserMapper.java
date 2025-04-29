package com.alia.back_end_service.jpa.user;

import com.alia.back_end_service.domain.user.User;

public interface UserMapper {
    User toDomain(UserEntity entity);
    UserEntity toEntity(User domain);
}
