package com.alia.back_end_service.api_rest.bot;

import com.alia.back_end_service.api_model.BotDTO;
import com.alia.back_end_service.api_model.BotSummaryDTO;
import com.alia.back_end_service.domain.bot.Bot;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class BotMapperAPIImpl implements BotMapperAPI {

    @Override
    public Bot toDomainRegister(BotDTO botRegister) {
        Bot bot = new Bot();
        bot.setName(botRegister.getName());
        bot.setDescription(botRegister.getDescription());
        bot.setEndpoint(botRegister.getEndpoint());
        bot.setToken(UUID.randomUUID().toString());
        return bot;
    }

    @Override
    public BotSummaryDTO toApiResponseSummary(Bot bot) {
        BotSummaryDTO botReturn = new BotSummaryDTO();
        botReturn.setName(bot.getName());
        botReturn.setDescription(bot.getDescription());
        return botReturn;
    }

    @Override
    public BotDTO toApiResponseDTO(Bot bot) {
        BotDTO botReturn = new BotDTO();
        botReturn.setName(bot.getName());
        botReturn.setDescription(bot.getDescription());
        botReturn.endpoint(bot.getEndpoint());
        botReturn.urlImagen(null);
        return botReturn;
    }
}
