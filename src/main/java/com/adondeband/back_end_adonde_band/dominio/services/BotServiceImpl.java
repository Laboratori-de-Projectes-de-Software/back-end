package com.adondeband.back_end_adonde_band.dominio.services;

import com.adondeband.back_end_adonde_band.dominio.puertos.in.BotService;
import com.adondeband.back_end_adonde_band.dominio.puertos.out.BotPort;
import com.adondeband.back_end_adonde_band.dominio.modelos.Bot;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BotServiceImpl implements BotService {

    private BotPort botPort;

    @Override
    public Bot crearBot(Bot bot) {
        if (botPort.findByNombre(bot.getNombre()).isEmpty()) {
            return botPort.save(bot);
        } else {
            throw new IllegalArgumentException("El nombre del bot ya existe");
        }
    }

    @Override
    public List<Bot> obtenerBotPorNombre(String nombre) {
        return botPort.findByNombre(nombre);
    }
}
