package com.alia.back_end_service.api_rest.user;

import com.alia.back_end_service.domain.bot.Bot;
import com.alia.back_end_service.domain.user.Role;
import com.alia.back_end_service.domain.user.User;
import com.alia.back_end_service.jpa.bot.BotEntity;

import java.util.List;

/*public class UserDTOGet {
    private String username;

    private String mail;

    private String foto;

    private List<Bot> bot;
}*/
public record UserDTOGet(String username, String email, String photo, List<Bot> bots) {
    public UserDTOGet(User user) {
        this(user.getUsername(), user.getMail(), user.getPhoto(), user.getBots()); // He quitado el rol, de momento no lo necesitamos
    }
}

