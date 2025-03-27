package com.alia.back_end_service.domain.bot.usecases;

import com.alia.back_end_service.domain.bot.Bot;
import com.alia.back_end_service.domain.bot.exceptions.BotAlreadyExistsException;
import com.alia.back_end_service.domain.bot.port.BotRegistrationPortAPI;
import com.alia.back_end_service.domain.bot.port.BotPortDB;

public class RegisterBotUseCase implements BotRegistrationPortAPI {

    private final BotPortDB botPortDB;

    public RegisterBotUseCase(BotPortDB botPortDB) {
        this.botPortDB = botPortDB;
    }

    @Override
    public Bot registerBot(Bot bot) {
        if (botPortDB.findByName(bot.getName()).isPresent()) {
            throw new BotAlreadyExistsException("Ya existe un bot con el nombre: " + bot.getName());
        }
        return botPortDB.save(bot);
    }
}
