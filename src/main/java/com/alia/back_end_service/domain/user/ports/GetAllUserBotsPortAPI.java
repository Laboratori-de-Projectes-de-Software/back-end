package com.alia.back_end_service.domain.user.ports;

import com.alia.back_end_service.domain.bot.Bot;

import java.util.List;

public interface GetAllUserBotsPortAPI {
    List<Bot> getAllUserBots(String username);
}
