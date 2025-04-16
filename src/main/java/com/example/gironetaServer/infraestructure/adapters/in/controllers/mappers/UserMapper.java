package com.example.gironetaServer.infraestructure.adapters.in.controllers.mappers;

import com.example.gironetaServer.domain.User;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.dto.*;
import com.example.gironetaServer.infraestructure.adapters.out.db.entities.UserEntity;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
public class UserMapper {
    public static User toDomain(UserEntity userEntity) {
        User user = new User();
        user.setId(userEntity.getId());
        user.setUsername(userEntity.getUsername());
        user.setEmail(userEntity.getEmail());
        user.setPassword(userEntity.getPassword());
        return user;
    }

    public static UserEntity toEntity(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setUsername(user.getUsername());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());
        return userEntity;
    }

    public static UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    public static User toAppObject(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        return user;
    }

    public static UserResponse toUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setUsername(user.getUsername());
        userResponse.setEmail(user.getEmail());
        userResponse.setPassword(user.getPassword());
        userResponse.setCreatedAt(user.getCreatedAt());
        userResponse.setUpdatedAt(user.getUpdatedAt());
        return userResponse;
    }

    public UserResponseDTO toUserResponseDTO(String token, long expiresInMillis, String username, Long id) {
        // Convertir expiresInMillis a LocalDateTime
        // expiresIn va a contener la hora de expiración de CET
        LocalDateTime expiresIn = Instant.ofEpochMilli(System.currentTimeMillis() + expiresInMillis)
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        // Crear el objeto UserResponseDTO y establecer los valores
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setToken(token);
        userResponseDTO.setExpiresIn(expiresIn);
        userResponseDTO.setUser(username);
        userResponseDTO.setUserId(id);

        return userResponseDTO;
    }

    public UserDTORegisterResponse toUserDTORegisterResponse(UserEntity userEntity) {
        UserDTORegisterResponse userDTORegisterResponse = new UserDTORegisterResponse();
        userDTORegisterResponse.setId(userEntity.getId());
        userDTORegisterResponse.setUser(userEntity.getUsername());
        userDTORegisterResponse.setMail(userEntity.getEmail());
        return userDTORegisterResponse;
    }
}