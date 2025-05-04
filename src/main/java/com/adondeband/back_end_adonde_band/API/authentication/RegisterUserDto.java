package com.adondeband.back_end_adonde_band.API.authentication;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserDto {
    @NotBlank(message = "El correo no puede estar vacío")
    private String mail;

    @NotBlank(message = "La contraseña no puede estar vacía")
    private String password;

    @NotBlank(message = "El nombre de usuario no puede estar vacío")
    private String user;

}
