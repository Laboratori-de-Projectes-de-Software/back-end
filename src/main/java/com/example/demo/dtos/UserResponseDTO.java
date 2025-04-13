package com.example.demo.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

@Getter
@Setter
@Accessors(chain = true)
public class UserResponseDTO {
    private String token;
    private Date expiresIn;
    private String user;
}
