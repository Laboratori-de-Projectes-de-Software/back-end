package com.example.back_end_eing.dto;

import lombok.Value;

@Value
public class RegisterUserDto {
    private String nombreUsuario;
    private String email;
    private String password;
}
