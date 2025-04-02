package com.alia.back_end_service.domain.bot.port;

import com.alia.back_end_service.domain.bot.Bot;

import java.util.Optional;

public interface BotGetPortApi {
    Bot findByName(String name);
    Bot findById(Integer id);
}
