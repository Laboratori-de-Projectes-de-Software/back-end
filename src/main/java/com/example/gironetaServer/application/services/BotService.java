package com.example.gironetaServer.application.services;

import com.example.gironetaServer.application.ports.BotRepository;
import com.example.gironetaServer.application.usecases.users.CreateBot;
import com.example.gironetaServer.application.usecases.users.GetBot;
import com.example.gironetaServer.domain.Bot;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BotService implements CreateBot, GetBot {

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

    @Override
    public Optional<Bot> getBotById(Long id) {
        return botRepository.findById(id);
    }

    @Override
    public List<Bot> getAllBots() {
        return botRepository.getAllBots();
    }

    @Override
    public List<Bot> getBotsByOwner(Long id) {
        return null;
    }

}
