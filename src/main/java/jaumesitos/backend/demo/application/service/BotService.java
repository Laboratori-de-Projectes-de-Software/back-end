package jaumesitos.backend.demo.application.service;

import jaumesitos.backend.demo.application.repository.IBotRepository;
import jaumesitos.backend.demo.domain.Bot;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BotService {
    private final IBotRepository botRepository;

    public BotService(IBotRepository botRepository) {
        this.botRepository = botRepository;
    }

    public Bot registerBot(Bot bot) {
        return botRepository.save(bot);
    }

    public Optional<Bot> getBotById(int id) {
        return botRepository.findById(id);
    }

    public List<Bot> getAllBots() {
        return botRepository.findAll();
    }

    public List<Bot> getBotsByOwner(int ownerId) { return botRepository.findByOwnerId(ownerId); }

    public Bot updateBot(int id, Bot updatedBot) {
        Optional<Bot> existing = botRepository.findById(id);
        if (existing.isEmpty()) {
            throw new RuntimeException("Bot no encontrado");
        }

        Bot bot = existing.get();

        bot.setName(updatedBot.getName());
        bot.setDescription(updatedBot.getDescription());
        bot.setUrlImage(updatedBot.getUrlImage());
        bot.setEndpoint(updatedBot.getEndpoint());

        return botRepository.save(bot);
    }
}
