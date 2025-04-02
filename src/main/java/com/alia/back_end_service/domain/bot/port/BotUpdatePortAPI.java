package com.alia.back_end_service.domain.bot.port;

import com.alia.back_end_service.domain.bot.Bot;

public interface BotUpdatePortAPI {
    void update(Bot bot, Integer id);
}
