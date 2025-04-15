package com.example.back_end_eing.services;

import com.example.back_end_eing.dto.BotDTO;

import com.example.back_end_eing.dto.BotResponseDTO;
import com.example.back_end_eing.dto.BotSummaryResponseDTO;
import com.example.back_end_eing.models.Bot;

import java.util.List;


public interface BotService {
    
    void BotRegistro(BotDTO botdto);

    List<BotSummaryResponseDTO> listarBots(Long userId);

    void actualizarBot(BotDTO botdto, Long id);

    BotResponseDTO obtenerBot(Long botId);

    List<BotSummaryResponseDTO> getAllBots();

    Bot getBotById(Long id);

    void deleteBot(Long id);
}
