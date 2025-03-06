package com.adondeband.back_end_adonde_band.api.dominio.services;

import com.adondeband.back_end_adonde_band.api.dominio.modelos.Liga;
import com.adondeband.back_end_adonde_band.api.dominio.puertos.LigaPort;

public class LigaService {
    private LigaPort ligaPort;

    public Liga insertarLiga(Liga liga) {
        if (ligaPort.findByNombre(liga.getNombre()).isEmpty()) {
            return ligaPort.save(liga);
        } else {
            throw new IllegalArgumentException("El nombre del bot ya existe");
        }
    }
}
