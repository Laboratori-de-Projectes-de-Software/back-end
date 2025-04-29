package com.alia.back_end_service.domain.bot.usecases;

import com.alia.back_end_service.domain.bot.Bot;
import com.alia.back_end_service.domain.bot.exceptions.BotAlreadyExistsException;
import com.alia.back_end_service.domain.bot.port.BotPortDB;
import com.alia.back_end_service.domain.bot.port.BotUpdatePortAPI;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateBotUseCase implements BotUpdatePortAPI {
    private final BotPortDB botPortDB;
    @Override
    public void update(Bot bot, Integer id) {

        botPortDB.findByName(bot.getName()).ifPresent(existingBot -> {
            if (!existingBot.getId().equals(id)) {
                throw new BotAlreadyExistsException("Ya existe un bot con ese nombre");
            }
        });


        botPortDB.findByEndpoint(bot.getEndpoint().toString()).ifPresent(existingBot -> {
            if (!existingBot.getId().equals(id)) {
                throw new BotAlreadyExistsException("Ya existe un bot con ese endpoint");
            }
        });

        botPortDB.update(bot, id);
    }
}
