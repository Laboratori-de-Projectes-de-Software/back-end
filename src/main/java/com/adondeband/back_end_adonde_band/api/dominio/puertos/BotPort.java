package com.adondeband.back_end_adonde_band.api.dominio.puertos;


import com.adondeband.back_end_adonde_band.api.dominio.modelos.Bot;

import java.util.List;

public interface BotPort {
    Bot save(Bot bot);

    List<Bot> findByNombre(String s);
}
