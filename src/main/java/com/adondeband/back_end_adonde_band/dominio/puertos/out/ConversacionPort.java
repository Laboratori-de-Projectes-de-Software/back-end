package com.adondeband.back_end_adonde_band.dominio.puertos.out;

import com.adondeband.back_end_adonde_band.dominio.modelos.Conversacion;

public interface ConversacionPort {
    Conversacion save(Conversacion conversacion);
}
