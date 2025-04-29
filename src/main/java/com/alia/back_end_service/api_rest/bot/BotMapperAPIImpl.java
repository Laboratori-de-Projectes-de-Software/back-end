package com.alia.back_end_service.api_rest.bot;

import com.alia.back_end_service.api_model.BotDTO;
import com.alia.back_end_service.api_model.BotSummaryResponseDTO;
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
        bot.setImg(botRegister.getUrlImagen());
        return bot;
    }

    @Override
    public BotSummaryResponseDTO toApiResponseSummary(Bot bot) {
        BotSummaryResponseDTO botReturn = new BotSummaryResponseDTO();
        botReturn.setId(bot.getId());
        botReturn.setName(bot.getName());
        botReturn.setDescription(bot.getDescription());
        botReturn.setUsername(bot.getUserId());
        botReturn.setEndpoint(bot.getEndpoint());
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
