package jaumesitos.backend.demo.application.service;

import jaumesitos.backend.demo.application.repository.IBotRepository;
import jaumesitos.backend.demo.domain.Bot;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class BotService {
    private final IBotRepository botRepository;

    public BotService(IBotRepository botRepository) {
        this.botRepository = botRepository;
    }

    public void registerBot(String name, String modelIA) {
        Bot bot = new Bot(UUID.randomUUID().toString(), name, modelIA, LocalDateTime.now());
        botRepository.save(bot);
    }

    public Optional<Bot> getBotById(String id) {
        return botRepository.findById(id);
    }

    public List<Bot> getAllBots() {
        return botRepository.findAll();
    }
}
