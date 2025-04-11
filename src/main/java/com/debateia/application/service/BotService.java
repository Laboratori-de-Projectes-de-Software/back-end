package com.debateia.application.service;

import com.debateia.adapter.out.persistence.entities.BotEntity;
import com.debateia.adapter.out.persistence.entities.UserEntity;
import com.debateia.application.ports.out.persistence.BotRepository;
import com.debateia.application.ports.out.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
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
            return botRepository.findByUserId(ownerId.get());
        } else {
            return botRepository.findAll();
        }
    }

    public BotEntity createBot(BotEntity botEntity, Integer userId) {
        Optional<UserEntity> owner = userRepository.findById(userId);
        if (owner.isPresent()) { // usuario existe
            botEntity.setUser(owner.get()); // anadir relacion del bot al usuario
            return botRepository.save(botEntity);
        } else {
            return null; // el controller trata el error
        }
    }

    public BotEntity getBotById(Integer botId) {
        Optional<BotEntity> botEntity = botRepository.findById(botId);
        return botEntity.orElse(null);
    }

    public BotEntity updateBot(Integer botId, BotEntity newBot) {
        Optional<BotEntity> currentBot = botRepository.findById(botId);
        if (currentBot.isEmpty()) {
            return null;
        }
        newBot.setWins(currentBot.get().getWins());
        newBot.setDraws(currentBot.get().getDraws());
        newBot.setLosses(currentBot.get().getLosses());
        return botRepository.save(newBot);
    }

}
