package com.adondeband.back_end_adonde_band.dominio.liga;

import org.springframework.stereotype.Service;

@Service
public class LigaImpl implements LigaService {

    private LigaPort ligaPort;

    public Liga insertarLiga(Liga liga) {
        return ligaPort.save(liga);
    }
}
