package com.adondeband.back_end_adonde_band.dominio.participacion;

import com.adondeband.back_end_adonde_band.dominio.liga.LigaPort;
import org.springframework.stereotype.Service;

@Service
public class ParticipacionImpl implements ParticipacionService {

    private final ParticipacionPort participacionPort;
    private final LigaPort ligaPort;

    public ParticipacionImpl(ParticipacionPort participacionPort, LigaPort ligaPort) {
        this.participacionPort = participacionPort;
        this.ligaPort = ligaPort;
    }
}
