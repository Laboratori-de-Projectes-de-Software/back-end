package com.adondeband.back_end_adonde_band.dominio.services;

import com.adondeband.back_end_adonde_band.dominio.modelos.Liga;
import com.adondeband.back_end_adonde_band.dominio.puertos.out.LigaPort;
import org.springframework.stereotype.Service;

@Service
public class LigaService {

    private LigaPort ligaPort;

    public Liga insertarLiga(Liga liga) {
        return ligaPort.save(liga);
    }
}
