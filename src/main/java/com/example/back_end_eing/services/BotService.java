package com.example.back_end_eing.services;

import com.example.back_end_eing.dto.BotDTO;

import com.example.back_end_eing.dto.BotResponseDTO;
import com.example.back_end_eing.dto.BotSummaryResponseDTO;

import java.util.List;


public interface BotService {
    
    public void BotRegistro(BotDTO botdto);

    List<BotSummaryResponseDTO> listarBots(Long userId);


    BotResponseDTO obtenerBot(Long botId);
}
