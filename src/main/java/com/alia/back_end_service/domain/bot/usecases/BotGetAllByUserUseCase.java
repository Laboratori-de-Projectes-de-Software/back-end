package com.alia.back_end_service.domain.bot.usecases;

import com.alia.back_end_service.domain.bot.Bot;
import com.alia.back_end_service.domain.bot.port.BotPortDB;
import com.alia.back_end_service.domain.bot.port.BotGetAllByUserPortAPI;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class BotGetAllByUserUseCase implements BotGetAllByUserPortAPI {
    private final BotPortDB botPortDB;
    @Override
    public List<Bot> getAllUserBots(String username) {
        return botPortDB.getAllUserBots(username);
    }
}
