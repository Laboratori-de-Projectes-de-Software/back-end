package com.adondeband.back_end_adonde_band.dominio.bot;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BotImpl implements BotService {

    private BotPort botPort;

    @Override
    public Bot crearBot(Bot bot) {
        if (botPort.findByNombre(bot.getNombre().value()).isEmpty()) {
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
