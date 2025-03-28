package com.alia.back_end_service.domain.bot.usecases;

import com.alia.back_end_service.domain.bot.Bot;
import com.alia.back_end_service.domain.bot.port.BotGetPortApi;
import com.alia.back_end_service.domain.bot.port.BotPortDB;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GetBotUseCase implements BotGetPortApi {

    private final BotPortDB botPortDB;

    @Override
    public Bot findByName(String name) {
        return botPortDB.findByName(name).orElse(null);
    }
}
