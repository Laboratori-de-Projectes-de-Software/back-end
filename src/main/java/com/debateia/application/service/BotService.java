package com.debateia.application.service;

import com.debateia.adapter.out.persistence.entities.BotEntity;
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

}
