package uib.lab.api.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uib.lab.api.domain.entity.Bot;
import uib.lab.api.domain.entity.User;
import uib.lab.api.infraestructura.jpaRepositories.BotJpaRepository;
import uib.lab.api.infraestructura.jpaRepositories.UserJpaRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BotService {
    private final BotJpaRepository botRepository;
    private final UserJpaRepository userRepository;

    public List<Bot> getBotsByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return botRepository.findByUser(user);
    }
}