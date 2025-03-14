package com.adondeband.back_end_adonde_band.dominio.puertos.in;
import com.adondeband.back_end_adonde_band.dominio.modelos.Bot;

import java.util.List;

public interface BotService {
    Bot crearBot(Bot bot);

    List<Bot> obtenerBotPorNombre(String nombre);
}
