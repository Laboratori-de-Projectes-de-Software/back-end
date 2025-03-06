package com.adondeband.back_end_adonde_band.dominio.puertos.out;

import com.adondeband.back_end_adonde_band.dominio.modelos.Bot;
import java.util.List;

public interface BotPort {
    Bot save(Bot bot);

    List<Bot> findByNombre(String s);
}
