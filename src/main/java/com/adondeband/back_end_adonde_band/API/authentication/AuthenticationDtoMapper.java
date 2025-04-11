package com.adondeband.back_end_adonde_band.API.authentication;

import com.adondeband.back_end_adonde_band.dominio.usuario.CorreoId;
import com.adondeband.back_end_adonde_band.dominio.usuario.Usuario;
import com.adondeband.back_end_adonde_band.dominio.usuario.UsuarioId;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthenticationDtoMapper{

    default Usuario registerUserDtotoDomain(RegisterUserDto registerUserDto){
        Usuario usuario = new Usuario();
        usuario.setCorreo(new CorreoId(registerUserDto.getMail()));
        usuario.setContrasena(registerUserDto.getPassword());
        usuario.setNombre(registerUserDto.getUser());
        return usuario;
    }

    default Usuario loginUserDtotoDomain(LoginUserDto loginUserDto){

        Usuario usuario = new Usuario();
        usuario.setNombre(loginUserDto.getUser());
        usuario.setContrasena(loginUserDto.getPassword());
        return usuario;
    }

    default RegisterUserDto userToRegisterDto(Usuario usuario){
        RegisterUserDto registerUserDto = new RegisterUserDto();
        registerUserDto.setMail(usuario.getCorreo().value());
        registerUserDto.setPassword(usuario.getContrasena());
        registerUserDto.setUser(usuario.getNombre());
        return registerUserDto;
    }

    default LoginUserDto userToLoginDTO(Usuario usuario){
        LoginUserDto loginUserDto = new LoginUserDto();
        loginUserDto.setUser(usuario.getNombre());
        loginUserDto.setPassword(usuario.getContrasena());
        return loginUserDto;
    }


}
