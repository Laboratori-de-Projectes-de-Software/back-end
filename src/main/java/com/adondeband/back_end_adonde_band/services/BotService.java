package com.adondeband.back_end_adonde_band.services;

import com.adondeband.back_end_adonde_band.api.DTO.BotDTO;
import com.adondeband.back_end_adonde_band.api.modelos.Bot;
import com.adondeband.back_end_adonde_band.repositories.BotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BotService {
    @Autowired
    private BotRepository botRepository;

    public BotDTO insertarBot(BotDTO botDTO) {
        if (botRepository.findByNombre(botDTO.getNombre()).isEmpty()) {
            Bot bot = new Bot(botDTO.getNombre(), botDTO.getDefensa());
            botRepository.save(bot);
            return botDTO;
        } else {
            throw new IllegalArgumentException("El nombre del bot ya existe");
        }
    }
}
