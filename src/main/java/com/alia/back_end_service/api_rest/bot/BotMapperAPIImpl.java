package com.alia.back_end_service.api_rest.bot;

import com.alia.back_end_service.api_model.BotRegister;
import com.alia.back_end_service.api_model.BotReturn;
import com.alia.back_end_service.domain.bot.Bot;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class BotMapperAPIImpl implements BotMapperAPI {
    @Override
    public BotReturn toApiResponse(Bot bot) {
        BotReturn botReturn = new BotReturn();
        botReturn.setName(bot.getName());
        botReturn.setDescription(bot.getDescription());
        botReturn.setUsername(bot.getUserId());
        return botReturn;
    }

    @Override
    public Bot toDomainRegister(BotRegister botRegister) {
        Bot bot = new Bot();
        bot.setName(botRegister.getName());
        bot.setDescription(botRegister.getDescription());
        bot.setEndpoint(botRegister.getEndpoint());
        bot.setToken(UUID.randomUUID().toString());
        bot.setUserId(botRegister.getUserId());
        return bot;
    }
}
