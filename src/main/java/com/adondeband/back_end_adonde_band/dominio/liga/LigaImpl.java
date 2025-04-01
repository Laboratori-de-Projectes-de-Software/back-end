package com.adondeband.back_end_adonde_band.dominio.liga;

import org.springframework.stereotype.Service;

@Service
public class LigaImpl implements LigaService {

    private LigaPort ligaPort;

    public LigaImpl(LigaPort ligaPort) {
        this.ligaPort = ligaPort;
    }

    @Override
    public Liga crearLiga(Liga liga) {
        return ligaPort.save(liga);
    }
}
