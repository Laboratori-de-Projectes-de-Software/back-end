package com.developers.iasuperleague.application.port.in;


import com.developers.iasuperleague.dtos.BotDTO;
import com.developers.iasuperleague.dtos.BotResponseDTO;
import com.developers.iasuperleague.dtos.BotSummaryResponseDTO;

import java.util.List;

/**
 * Define los casos de uso para la gestión de ligas.
 */
public interface BotUseCase {
    BotResponseDTO createBot(BotDTO botDTO);
    BotResponseDTO getBot(Integer leagueId);
    BotResponseDTO updateBot(Integer botId, BotDTO botDTO);
    List<BotSummaryResponseDTO> listBots();
    List<BotSummaryResponseDTO> listBotsByOwner(Integer ownerId); // solo de ese usuario
}
