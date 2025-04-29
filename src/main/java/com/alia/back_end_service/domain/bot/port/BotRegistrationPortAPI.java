package com.alia.back_end_service.domain.bot.port;

import com.alia.back_end_service.domain.bot.Bot;

public interface BotRegistrationPortAPI {
    Bot registerBot(Bot bot);
}
