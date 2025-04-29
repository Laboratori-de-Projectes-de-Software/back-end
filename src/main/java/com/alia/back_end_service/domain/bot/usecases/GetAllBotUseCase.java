package com.alia.back_end_service.domain.bot.usecases;

import com.alia.back_end_service.domain.bot.Bot;
import com.alia.back_end_service.domain.bot.port.BotGetAllPortAPI;
import com.alia.back_end_service.domain.bot.port.BotPortDB;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class GetAllBotUseCase implements BotGetAllPortAPI {

    private final BotPortDB botPortDB;

    @Override
    public List<Bot> getAllBots() {
        return botPortDB.getBots();
    }
}
