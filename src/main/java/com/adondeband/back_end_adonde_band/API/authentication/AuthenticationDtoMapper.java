package com.adondeband.back_end_adonde_band.API.authentication;

import com.adondeband.back_end_adonde_band.dominio.usuario.Usuario;
import com.adondeband.back_end_adonde_band.dominio.usuario.UsuarioId;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public class AuthenticationDtoMapper{

    public Usuario toDomain(RegisterUserDto registerUserDto){
        Usuario usuario = new Usuario();
        usuario.setCorreo(registerUserDto.getEmail());
        usuario.setContrasena(registerUserDto.getPassword());
        usuario.setNombre(new UsuarioId(registerUserDto.getFullName()));
        return usuario;
    }

    public Usuario toDomain(LoginUserDto loginUserDto){
        Usuario usuario = new Usuario();
        usuario.setCorreo(loginUserDto.getEmail());
        usuario.setContrasena(loginUserDto.getPassword());
        return usuario;
    }

    public RegisterUserDto UserToRegisterDto(Usuario usuario){
        RegisterUserDto registerUserDto = new RegisterUserDto();
        registerUserDto.setEmail(usuario.getCorreo());
        registerUserDto.setPassword(usuario.getContrasena());
        registerUserDto.setFullName(usuario.getNombre().value());
        return registerUserDto;
    }

    public LoginUserDto UserToLoginDTO(Usuario usuario){
        LoginUserDto loginUserDto = new LoginUserDto();
        loginUserDto.setEmail(usuario.getCorreo());
        loginUserDto.setPassword(usuario.getContrasena());
        return loginUserDto;
    }


}
