package com.adondeband.back_end_adonde_band.dominio.bot;

import java.util.List;

public interface BotPort {
    Bot save(Bot bot);

    List<Bot> findByNombre(String s);
}
