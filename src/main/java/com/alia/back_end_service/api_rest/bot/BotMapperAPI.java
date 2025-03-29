package com.alia.back_end_service.api_rest.bot;

import com.alia.back_end_service.api_model.BotRegister;
import com.alia.back_end_service.api_model.BotReturn;
import com.alia.back_end_service.domain.bot.Bot;

public interface BotMapperAPI {
    Bot toDomainRegister(BotRegister botRegister);
    BotReturn toApiResponse(Bot bot);
}
