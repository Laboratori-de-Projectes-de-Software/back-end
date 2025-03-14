package com.adondeband.back_end_adonde_band.dominio.bot;

import java.util.List;

public interface BotService {
    Bot crearBot(Bot bot);

    List<Bot> obtenerBotPorNombre(String nombre);
}
