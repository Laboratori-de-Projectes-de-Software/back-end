package com.debateia.application.service;

import com.debateia.adapter.out.persistence.AIEntity;
import com.debateia.application.ports.out.persistence.AIRepository;
import com.debateia.application.ports.out.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AIService {
    private final AIRepository aiRepository;
    private final UserRepository userRepository;

    public List<AIEntity> getAIs(Optional<Integer> ownerId) {
        if (ownerId.isPresent()) {
            return aiRepository.findByUserId(ownerId.get());
        } else {
            return aiRepository.findAll();
        }
    }

}
