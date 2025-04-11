package com.adondeband.back_end_adonde_band.API.authentication;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class LoginResponse {

    private String token;

    private long expiresIn;

    private String user;
}

