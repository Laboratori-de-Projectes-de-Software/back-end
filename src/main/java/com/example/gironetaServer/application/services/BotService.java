package com.example.gironetaServer.application.services;

import com.example.gironetaServer.application.ports.BotRepository;
import com.example.gironetaServer.application.usecases.users.CreateBot;
import com.example.gironetaServer.domain.Bot;
import com.example.gironetaServer.domain.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class BotService implements CreateBot {

    private final BotRepository botRepository;

    public BotService(BotRepository botRepository) {
        this.botRepository = botRepository;
    }

    @Override
    public Bot createBot(Bot bot) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usuario = authentication.getName();
        bot.setUsuario_correo(usuario);
        return botRepository.save(bot);
    }
}
