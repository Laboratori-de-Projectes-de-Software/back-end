package com.alia.back_end_service.domain.bot.port;

import com.alia.back_end_service.domain.bot.Bot;

import java.util.List;

public interface BotGetAllPortAPI {
    List<Bot> getAllBots();
}
