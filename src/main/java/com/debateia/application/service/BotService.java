package com.debateia.application.service;

import com.debateia.adapter.out.persistence.entities.BotEntity;
import com.debateia.adapter.out.persistence.entities.UserEntity;
import com.debateia.application.ports.out.persistence.BotRepository;
import com.debateia.application.ports.out.persistence.UserRepository;
import com.debateia.domain.Bot;
import com.debateia.domain.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BotService {
    private final BotRepository botRepository;
    private final UserRepository userRepository;

    public List<Bot> getBots(Optional<Integer> ownerId) {
        if (ownerId.isPresent()) {
            Optional<User> user = userRepository.findById(ownerId.get());
            if (user.isEmpty()) { // solicitud de usuario inexistente
                throw new EntityNotFoundException("Usuario con ID " + ownerId.get() + " no encontrado");
            }
            return botRepository.findByUserId(ownerId.get());
        } else {
            return botRepository.findAll();
        }
    }

    @Transactional
    public Bot createBot(Bot bot, Integer userId) {
        Optional<User> owner = userRepository.findById(userId);
        if (owner.isPresent()) { // usuario existe
            if (botRepository.exists(bot.getName())) { //
                throw new DataIntegrityViolationException("Ya existe un bot con ese nombre");
            }
            bot.setUserId(userId); // anadir relacion del bot al usuario
            return botRepository.save(bot);
        }
        throw new EntityNotFoundException("El usuario con ID "+userId+" no existe");
    }

    public Bot getBotById(Integer botId) {
        Optional<Bot> bot = botRepository.findById(botId);
        if (bot.isEmpty()) {
            throw new EntityNotFoundException("El bot con ID "+botId+" no existe");
        }
        return bot.get();
    }

    public Bot updateBot(Integer botId, Integer userId, Bot newBot) {
        Optional<Bot> currentBot = botRepository.findById(botId);
        if (currentBot.isEmpty()) {
            throw new EntityNotFoundException("El bot con ID \""+botId+"\" a actualizar no existe");
        }
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new EntityNotFoundException("El user con ID \""+userId+"\" propietario del bot no existe");
        }
        newBot.setWins(currentBot.get().getWins());
        newBot.setDraws(currentBot.get().getDraws());
        newBot.setLosses(currentBot.get().getLosses());
        newBot.setUserId(user.get().getUserId());
        newBot.setId(botId); // IMPORTANTE!!! Para que save haga UPDATE en vez de INSERT
        try {
            return botRepository.save(newBot);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Ya existe un bot con nombre \""+newBot.getName()+"\"");
        }
    }

}
