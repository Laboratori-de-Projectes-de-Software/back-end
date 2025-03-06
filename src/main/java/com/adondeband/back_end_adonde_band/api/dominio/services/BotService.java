package com.adondeband.back_end_adonde_band.api.dominio.services;

import com.adondeband.back_end_adonde_band.api.dominio.puertos.BotPort;
import com.adondeband.back_end_adonde_band.api.dominio.modelos.Bot;
import org.springframework.stereotype.Service;

@Service
public class BotService {

    private BotPort botPort;

    public Bot insertarBot(Bot bot) {
        if (botPort.findByNombre(bot.getNombre()).isEmpty()) {
            return botPort.save(bot);
        } else {
            throw new IllegalArgumentException("El nombre del bot ya existe");
        }
    }
}
