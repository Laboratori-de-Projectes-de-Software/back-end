package uib.lab.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uib.lab.api.entity.Bot;
import uib.lab.api.entity.User;
import uib.lab.api.repository.BotRepository;
import uib.lab.api.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BotService {
    private final BotRepository botRepository;
    private final UserRepository userRepository;

    public List<Bot> getBotsByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return botRepository.findByUser(user);
    }
}