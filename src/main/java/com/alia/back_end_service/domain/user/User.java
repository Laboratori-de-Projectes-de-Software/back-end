package com.alia.back_end_service.domain.user;

import com.alia.back_end_service.domain.bot.Bot;
import com.alia.back_end_service.jpa.bot.BotEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    private String username;

    private String mail;

    private String password;

    private String photo;

    private Role role;

    //private List<Bot> bots;
}
