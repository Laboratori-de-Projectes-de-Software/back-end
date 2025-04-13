package com.example.demo.application.port.in;

import com.example.demo.dtos.BotDTO;
import com.example.demo.dtos.BotDTO;
import com.example.demo.dtos.BotResponseDTO;
import com.example.demo.dtos.BotSummaryResponseDTO;

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
