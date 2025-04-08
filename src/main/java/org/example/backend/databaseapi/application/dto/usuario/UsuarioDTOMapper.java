package org.example.backend.databaseapi.application.dto.usuario;

import org.example.backend.databaseapi.domain.usuario.Email;
import org.example.backend.databaseapi.domain.usuario.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT)
public interface UsuarioDTOMapper {


    @Mapping(target = "nombre", source = "user")
    Usuario toUsuario(UserDTORegister request);

    default Email toEmail(String email){
        return new Email(email);
    }
}
