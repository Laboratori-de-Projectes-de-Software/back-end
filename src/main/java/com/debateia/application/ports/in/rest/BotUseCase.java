package com.debateia.application.ports.in.rest;

import com.debateia.domain.Bot;
import java.util.List;
import java.util.Optional;

public interface BotUseCase {
    public List<Bot> getBots(Optional<Integer> ownerId);
    public Bot createBot(Bot bot, Integer userId);
    public Bot getBotById(Integer botId);
    public Bot updateBot(Integer botId, Integer userId, Bot newBot);
}
