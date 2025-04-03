package com.example.gironetaServer.application.services;

import com.example.gironetaServer.application.ports.BotRepository;
import com.example.gironetaServer.application.usecases.users.CreateBot;
import com.example.gironetaServer.application.usecases.users.GetBot;
import com.example.gironetaServer.application.usecases.users.UpdateBot;
import com.example.gironetaServer.domain.Bot;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BotService implements CreateBot, GetBot, UpdateBot {

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
        return botRepository.getBotsByOwner(id);
    }

    @Override
    public void updateBot(Long botId, Bot bot) {
        Optional<Bot> existingBotOptional = botRepository.findById(botId);

        if (existingBotOptional.isPresent()) {
            Bot existingBot = existingBotOptional.get();

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentUserEmail = authentication.getName();

            // Verificar si el bot pertenece al usuario autenticado
            if (existingBot.getUsuario_correo().equals(currentUserEmail)) {
                // Actualizar solo los campos permitidos
                existingBot.setName(bot.getName());
                existingBot.setDescripcion(bot.getDescripcion());
                existingBot.setUrlImagen(bot.getUrlImagen());
                existingBot.setEndpoint(bot.getEndpoint());

                // Guardar el bot actualizado
                botRepository.save(existingBot);
            } else {
                // Manejar el caso en que el bot no pertenece al usuario autenticado
                throw new SecurityException("You do not have permission to update this bot.");
            }
        } else {
            // Manejar el caso en que el bot no existe (puedes lanzar una excepción o registrar un error)
            throw new IllegalArgumentException("Bot not found with ID: " + botId);
        }
    }
}
