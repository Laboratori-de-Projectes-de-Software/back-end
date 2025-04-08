package jaumesitos.backend.demo.infrastructure.res.mapper;

import jaumesitos.backend.demo.domain.User;
import jaumesitos.backend.demo.infrastructure.res.dto.UserDTORegister;
import jaumesitos.backend.demo.infrastructure.res.dto.UserResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserDTOMapper {
    User toDomain(UserDTORegister userDTO);
    UserDTORegister toDTO(User user);

    @Mapping(source = "name", target = "user")
    @Mapping(source = "id", target = "userId")
    UserResponseDTO toResponseDTO(User user);
}
