package com.example.back_end_eing.services;

import com.example.back_end_eing.dto.BotResponseDTO;
import com.example.back_end_eing.dto.BotSummaryResponseDTO;

import java.util.List;

public interface BotService {
    
    public void BotRegistro(String nombre, String descripcion, String foto, Integer victorias, Integer numJornadas, String API, Long id);

    List<BotSummaryResponseDTO> listarBots(Long userId);


    BotResponseDTO obtenerBot(Long botId);
}
