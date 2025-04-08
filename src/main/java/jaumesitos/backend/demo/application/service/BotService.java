package jaumesitos.backend.demo.application.service;

import jaumesitos.backend.demo.application.repository.IBotRepository;
import jaumesitos.backend.demo.domain.Bot;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class BotService {
    private final IBotRepository botRepository;

    public BotService(IBotRepository botRepository) {
        this.botRepository = botRepository;
    }

    public Bot registerBot(Bot bot) {
        return botRepository.save(bot);
    }

    public Optional<Bot> getBotById(String id) {
        return botRepository.findById(id);
    }

    public List<Bot> getAllBots() {
        return botRepository.findAll();
    }

    public List<Bot> getBotsByOwner(int ownerId) { return botRepository.findByOwnerId(ownerId); }
}
