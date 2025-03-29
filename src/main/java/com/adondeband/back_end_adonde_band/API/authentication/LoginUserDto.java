package com.adondeband.back_end_adonde_band.API.authentication;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUserDto {
    private String email;

    private String password;

}