package jaumesitos.backend.demo.infrastructure.res.mapper;

import jaumesitos.backend.demo.domain.User;
import jaumesitos.backend.demo.infrastructure.res.dto.UserDTORegister;
import jaumesitos.backend.demo.infrastructure.res.dto.UserResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserDTOMapper {

    @Mapping(source = "user", target = "name")
    @Mapping(source = "mail", target = "email")
    User toDomain(UserDTORegister userDTO);

    @Mapping(source = "name", target = "user")
    @Mapping(source = "email", target = "mail")
    UserDTORegister toDTO(User user);


    @Mapping(source = "id", target = "userId")
    @Mapping(source = "name", target = "user")
    UserResponseDTO toResponseDTO(User user);
}
