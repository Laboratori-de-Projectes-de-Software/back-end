package com.example.gironetaServer.infraestructure.adapters.in.controllers.mappers;

import com.example.gironetaServer.domain.User;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.dto.UserDto;

public class UserMapper {

    public static UserDto toUserDto(User user) {
        return new UserDto(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(), user.getCreatedAt(),
                user.getUpdatedAt());
    }

    public static User toAppObject(UserDto userDto) {
        User user = new User(userDto.getId(), userDto.getUsername(), userDto.getEmail(), userDto.getPassword(),
                userDto.getCreatedAt(), userDto.getUpdatedAt());
        return user;
    }
}