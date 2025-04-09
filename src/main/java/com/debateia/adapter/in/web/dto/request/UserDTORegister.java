package com.debateia.adapter.in.web.dto.request;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserDTORegister {
    private String user;
    private String mail;
    private String password;
}