package com.example.gironetaServer.infraestructure.adapters.in.controllers.mappers;

import com.example.gironetaServer.domain.Bot;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.dto.BotSummaryDto;

public class BotSummaryMapper {


    public BotSummaryMapper() {
    }

    public static BotSummaryDto toBotSummaryDto(Bot bot) {
        return new BotSummaryDto(
                bot.getId(),
                bot.getName(),
                bot.getUrlImagen(),
                bot.getUsuario_id()
        );
    }
}
