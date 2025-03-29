package com.alia.back_end_service.domain.user.usecases;

import com.alia.back_end_service.domain.bot.Bot;
import com.alia.back_end_service.domain.bot.port.BotPortDB;
import com.alia.back_end_service.domain.user.ports.GetAllUserBotsPortAPI;
import com.alia.back_end_service.domain.user.ports.UserPortDB;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class GetAllUserBotsUseCase implements GetAllUserBotsPortAPI {
    private final BotPortDB botPortDB;
    @Override
    public List<Bot> getAllUserBots(String username) {
        return botPortDB.getAllUserBots(username);
    }
}
