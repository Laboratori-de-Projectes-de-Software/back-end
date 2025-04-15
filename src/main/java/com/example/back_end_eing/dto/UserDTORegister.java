package com.example.back_end_eing.dto;

import lombok.Value;

@Value
public class UserDTORegister {
    private String user;
    private String mail;
    private String password;
}
