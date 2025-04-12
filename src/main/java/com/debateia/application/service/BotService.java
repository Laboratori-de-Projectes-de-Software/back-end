package com.debateia.application.service;

import com.debateia.adapter.out.persistence.entities.BotEntity;
import com.debateia.adapter.out.persistence.entities.UserEntity;
import com.debateia.application.ports.out.persistence.BotRepository;
import com.debateia.application.ports.out.persistence.UserRepository;
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

    public List<BotEntity> getBots(Optional<Integer> ownerId) {
        if (ownerId.isPresent()) {
            Optional<UserEntity> user = userRepository.findById(ownerId.get());
            if (user.isEmpty()) { // solicitud de usuario inexistente
                throw new EntityNotFoundException("Usuario con ID " + ownerId.get() + " no encontrado");
            }
            return botRepository.findByUserId(ownerId.get());
        } else {
            return botRepository.findAll();
        }
    }

    @Transactional
    public BotEntity createBot(BotEntity botEntity, Integer userId) {
        Optional<UserEntity> owner = userRepository.findById(userId);

        if (owner.isPresent()) { // usuario existe
            if (botRepository.exists(botEntity.getName())) { //
                throw new DataIntegrityViolationException("Ya existe un bot con ese nombre");
            }
            botEntity.setUser(owner.get()); // anadir relacion del bot al usuario
            return botRepository.save(botEntity);
        }
        throw new EntityNotFoundException("El usuario con ID "+userId+" no existe");
    }

    public BotEntity getBotById(Integer botId) {
        Optional<BotEntity> botEntity = botRepository.findById(botId);
        if (botEntity.isEmpty()) {
            throw new EntityNotFoundException("El bot con ID "+botId+" no existe");
        }
        return botEntity.get();
    }

    public BotEntity updateBot(Integer botId, Integer userId, BotEntity newBot) {
        Optional<BotEntity> currentBot = botRepository.findById(botId);
        if (currentBot.isEmpty()) {
            throw new EntityNotFoundException("El bot con ID \""+botId+"\" a actualizar no existe");
        }
        Optional<UserEntity> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new EntityNotFoundException("El user con ID \""+userId+"\" propietario del bot no existe");
        }
        newBot.setWins(currentBot.get().getWins());
        newBot.setDraws(currentBot.get().getDraws());
        newBot.setLosses(currentBot.get().getLosses());
        newBot.setUser(user.get());
        newBot.setId(botId); // IMPORTANTE!!! Para que save haga UPDATE en vez de INSERT
        try {
            return botRepository.save(newBot);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Ya existe un bot con nombre \""+newBot.getName()+"\"");
        }
    }

}
