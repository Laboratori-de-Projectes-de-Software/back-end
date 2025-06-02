package com.debateia.application.ports.in.rest;

import com.debateia.domain.Bot;
import java.util.List;
import java.util.Optional;

public interface BotUseCase {
    List<Bot> getBots(Optional<Integer> ownerId);
    Bot createBot(Bot bot, Integer userId);
    Bot getBotById(Integer botId);
    Bot updateBot(Integer botId, Integer userId, Bot newBot);
}
