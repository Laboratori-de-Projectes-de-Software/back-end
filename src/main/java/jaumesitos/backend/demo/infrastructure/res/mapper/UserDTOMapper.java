package jaumesitos.backend.demo.infrastructure.res.mapper;

import jaumesitos.backend.demo.domain.User;
import jaumesitos.backend.demo.infrastructure.res.dto.UserDTORegister;
import jaumesitos.backend.demo.infrastructure.res.dto.UserResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDTOMapper {
    User toDomain(UserDTORegister userDTO);
    UserDTORegister toDTO(User user);

    UserResponseDTO toResponseDTO(User user);
}
