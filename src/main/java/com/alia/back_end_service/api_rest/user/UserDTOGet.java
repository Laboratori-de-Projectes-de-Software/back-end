package com.alia.back_end_service.api_rest.user;

import com.alia.back_end_service.domain.bot.Bot;
import com.alia.back_end_service.domain.user.Role;
import com.alia.back_end_service.domain.user.User;
import com.alia.back_end_service.jpa.bot.BotEntity;

import java.util.List;


public record UserDTOGet(String username, String email, List<Bot> bots) {
    public UserDTOGet(User user) {
        this(user.getUsername(), user.getMail(), user.getBots()); // He quitado el rol, de momento no lo necesitamos
    }
}

