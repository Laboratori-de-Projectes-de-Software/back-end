package com.alia.back_end_service.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    private Integer id;

    private String username;

    private String mail;

    private String password;

    private String token;
    private Long expiresIn;
}
