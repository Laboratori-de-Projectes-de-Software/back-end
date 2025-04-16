package com.alia.back_end_service.api_rest.bot;

import com.alia.back_end_service.api_model.BotDTO;
import com.alia.back_end_service.api_model.BotSummaryResponseDTO;
import com.alia.back_end_service.domain.bot.Bot;

public interface BotMapperAPI {
    Bot toDomainRegister(BotDTO botRegister);
    BotDTO toApiResponseDTO(Bot bot);
    BotSummaryResponseDTO toApiResponseSummary(Bot bot);
}
