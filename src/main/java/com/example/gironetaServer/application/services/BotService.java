package com.example.gironetaServer.application.services;

import com.example.gironetaServer.application.ports.BotRepository;
import com.example.gironetaServer.application.ports.ParticipacionRepository;
import com.example.gironetaServer.application.usecases.users.CreateBot;
import com.example.gironetaServer.application.usecases.users.GetBot;
import com.example.gironetaServer.application.usecases.users.UpdateBot;
import com.example.gironetaServer.domain.Bot;
import com.example.gironetaServer.domain.exceptions.ConflictException;
import com.example.gironetaServer.domain.exceptions.ForbiddenException;
import com.example.gironetaServer.domain.exceptions.ResourceNotFoundException;
import com.example.gironetaServer.domain.exceptions.UnauthorizedException;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.dto.ParticipationResponseDto;
import com.example.gironetaServer.infraestructure.adapters.out.db.entities.ParticipacionEntity;
import com.example.gironetaServer.infraestructure.adapters.out.db.entities.UserEntity;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BotService implements CreateBot, GetBot, UpdateBot {

    private final BotRepository botRepository;
    private final ParticipacionRepository participacionRepository;

    public BotService(BotRepository botRepository, ParticipacionRepository participacionRepository) {
        this.botRepository = botRepository;
        this.participacionRepository = participacionRepository;
    }

    @Override
    public Bot createBot(Bot bot) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UnauthorizedException("Usuario no autenticado");
        }
        UserEntity authenticatedUser = (UserEntity) authentication.getPrincipal();
        Long userId = authenticatedUser.getId();
        bot.setUsuario_id(userId);
        bot.setnDraws(0);
        bot.setnLosses(0);
        bot.setnWins(0);

        // Validar los campos obligatorios del bot
        if (bot == null ||
                bot.getName() == null || bot.getName().trim().isEmpty() ||
                bot.getDescripcion() == null || bot.getDescripcion().trim().isEmpty() ||
                bot.getUrlImagen() == null || bot.getUrlImagen().trim().isEmpty() ||
                bot.getEndpoint() == null || bot.getEndpoint().trim().isEmpty()) {
            throw new IllegalArgumentException("Todos los campos obligatorios del bot deben ser proporcionados (name, descripcion, urlImagen, endpoint).");
        }

        try {
            return botRepository.save(bot);
        } catch (DataIntegrityViolationException e) {
            // Ejemplo: Si el nombre del bot debe ser único por usuario
            if (e.getMessage().contains("UNIQUE constraint") && e.getMessage().contains("name") && e.getMessage().contains(userId.toString())) {
                throw new ConflictException("Ya existe un bot con este nombre para tu usuario.");
            }
            throw new ConflictException("Error al crear el bot: " + e.getMessage());
        } catch (Exception e) {
            throw new ConflictException("Error inesperado al crear el bot: " + e.getMessage());
        }
    }

    @Override
    public Optional<Bot> getBotById(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UnauthorizedException("Usuario no autenticado");
        }

        List<ParticipacionEntity> participations = participacionRepository.findByBotId(id);

        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID del bot no puede ser nulo o menor que cero.");
        }
        Optional<Bot> bot = botRepository.findById(id);

        int nWins = 0;
        int nLosses = 0;
        int nDraws = 0;

        // Si no tiene participaciones, obtenemos sus estadisticas
        if (participations != null || !participations.isEmpty()) {
            for(ParticipacionEntity participation : participations) {
                nWins += participation.getNumGanados();
                nLosses += participation.getNumPerdidos();
                nDraws += participation.getNumEmpatados();
            }
        }

        if (bot.isEmpty()) {
            throw new ResourceNotFoundException("Bot no encontrado con ID: " + id);
        }

        bot.get().setnWins(nWins);
        bot.get().setnLosses(nLosses);
        bot.get().setnDraws(nDraws);

        return bot;
    }

    @Override
    public List<Bot> getAllBots() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UnauthorizedException("Usuario no autenticado para acceder a todos los bots.");
        }
        try {
            List<Bot> bots = botRepository.getAllBots();
            if (bots == null) {
                return List.of(); // Devuelve una lista vacía en lugar de null
            }
            return bots;
        } catch (Exception e) {
            throw new ConflictException("Error al obtener todos los bots: " + e.getMessage());
        }
    }

    @Override
    public List<Bot> getBotsByOwner(Long userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UnauthorizedException("Usuario no autenticado");
        }

        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("El ID del usuario no puede ser nulo o menor que cero.");
        }
        try {
            List<Bot> bots = botRepository.getBotsByOwner(userId);
            if (bots == null) {
                return List.of(); // Devuelve una lista vacía en lugar de null
            }
            return bots;
        } catch (Exception e) {
            throw new ConflictException("Error al obtener los bots del usuario con ID: " + userId + ": " + e.getMessage());
        }
    }

    @Override
    public Bot updateBot(Long botId, Bot bot) {
        if (botId == null || botId <= 0) {
            throw new IllegalArgumentException("El ID del bot no puede ser nulo o menor que cero.");
        }
        if (bot == null) {
            throw new IllegalArgumentException("Los datos del bot para actualizar no pueden ser nulos.");
        }
        if (bot.getName() == null || bot.getName().trim().isEmpty() ||
                bot.getDescripcion() == null || bot.getDescripcion().trim().isEmpty() ||
                bot.getUrlImagen() == null || bot.getUrlImagen().trim().isEmpty() ||
                bot.getEndpoint() == null || bot.getEndpoint().trim().isEmpty()) {
            throw new IllegalArgumentException("Todos los campos obligatorios del bot deben ser proporcionados para la actualización (name, descripcion, urlImagen, endpoint).");
        }

        Optional<Bot> existingBotOptional = botRepository.findById(botId);

        if (existingBotOptional.isPresent()) {
            Bot existingBot = existingBotOptional.get();

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || !authentication.isAuthenticated()) {
                throw new UnauthorizedException("Usuario no autenticado.");
            }
            UserEntity authenticatedUser = (UserEntity) authentication.getPrincipal();
            Long currentUserId = authenticatedUser.getId();

            if (!existingBot.getUsuario_id().equals(currentUserId)) {
                throw new ForbiddenException("No tienes permiso para actualizar este bot.");
            }

            existingBot.setName(bot.getName());
            existingBot.setDescripcion(bot.getDescripcion());
            existingBot.setUrlImagen(bot.getUrlImagen());
            existingBot.setEndpoint(bot.getEndpoint());

            try {
                return botRepository.save(existingBot);
            } catch (DataIntegrityViolationException e) {
                // Ejemplo: Si el nuevo nombre del bot ya existe para el usuario
                if (e.getMessage().contains("UNIQUE constraint") && e.getMessage().contains("name") && e.getMessage().contains(currentUserId.toString())) {
                    throw new ConflictException("Ya existe un bot con este nombre para tu usuario.");
                }
                throw new ConflictException("Error al actualizar el bot: " + e.getMessage());
            } catch (Exception e) {
                throw new ConflictException("Error inesperado al actualizar el bot: " + e.getMessage());
            }

        } else {
            throw new ResourceNotFoundException("Bot no encontrado con ID: " + botId);
        }
    }
}