package com.adondeband.back_end_adonde_band.API.authentication;

import com.adondeband.back_end_adonde_band.dominio.usuario.Usuario;
import com.adondeband.back_end_adonde_band.dominio.usuario.UsuarioId;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthenticationDtoMapper{

    default Usuario registerUserDtotoDomain(RegisterUserDto registerUserDto){
        Usuario usuario = new Usuario();
        usuario.setCorreo(registerUserDto.getEmail());
        usuario.setContrasena(registerUserDto.getPassword());
        usuario.setNombre(new UsuarioId(registerUserDto.getUser()));
        return usuario;
    }

    default Usuario loginUserDtotoDomain(LoginUserDto loginUserDto){

        Usuario usuario = new Usuario();
        usuario.setNombre(new UsuarioId(loginUserDto.getUser()));
        usuario.setContrasena(loginUserDto.getPassword());
        return usuario;
    }

    default RegisterUserDto userToRegisterDto(Usuario usuario){
        RegisterUserDto registerUserDto = new RegisterUserDto();
        registerUserDto.setEmail(usuario.getCorreo());
        registerUserDto.setPassword(usuario.getContrasena());
        registerUserDto.setUser(usuario.getNombre().value());
        return registerUserDto;
    }

    default LoginUserDto userToLoginDTO(Usuario usuario){
        LoginUserDto loginUserDto = new LoginUserDto();
        loginUserDto.setUser(usuario.getNombre().value());
        loginUserDto.setPassword(usuario.getContrasena());
        return loginUserDto;
    }


}
