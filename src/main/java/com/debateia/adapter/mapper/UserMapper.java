package com.debateia.adapter.mapper;

import com.debateia.adapter.in.web.dto.request.UserDTOLogin;
import com.debateia.adapter.in.web.dto.request.UserDTORegister;
import com.debateia.adapter.in.web.dto.response.UserResponseDTO;
import com.debateia.adapter.out.persistence.entities.UserEntity;
import com.debateia.domain.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring", 
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {

    // Map from UserDTORegister to domain User
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "token", ignore = true)
    @Mapping(target = "expiresIn", ignore = true)
    @Mapping(target = "username", source = "user")
    @Mapping(target = "mail", source = "mail")
    User toUserDomain(UserDTORegister userDTORegister);

    // Map from UserDTOLogin to domain User
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "mail", ignore = true)
    @Mapping(target = "token", ignore = true)
    @Mapping(target = "expiresIn", ignore = true)
    @Mapping(target = "username", source = "user")
    User toUserDomain(UserDTOLogin userDTOLogin);

    // Map from domain User to UserEntity
    @Mapping(target = "id", source = "userId")
    @Mapping(target = "league", ignore = true)
    @Mapping(target = "bots", ignore = true)
    UserEntity toUserEntity(User user);

    // Map from UserEntity to domain User
    @Mapping(target = "userId", source = "id")
    @Mapping(target = "token", ignore = true)
    @Mapping(target = "expiresIn", ignore = true)
    User toUserDomain(UserEntity userEntity);

    // Map from domain User to UserResponseDTO
    @Mapping(target = "user", source = "username")
    UserResponseDTO toUserResponseDTO(User user);

    // Update UserEntity from User domain
    @Mapping(target = "id", source = "userId")
    @Mapping(target = "league", ignore = true)
    @Mapping(target = "bots", ignore = true)
    void updateUserEntityFromDomain(User source, @MappingTarget UserEntity target);

    // Update existing User domain from the values of a UserEntity
    @Mapping(target = "userId", source = "id")
    void updateUserDomainFromEntity(UserEntity source, @MappingTarget User target);
}
