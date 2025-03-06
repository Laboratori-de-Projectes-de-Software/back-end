package com.adondeband.back_end_adonde_band.dominio.services;

import com.adondeband.back_end_adonde_band.dominio.modelos.Participacion;
import com.adondeband.back_end_adonde_band.dominio.puertos.out.ParticipacionPort;
import org.springframework.stereotype.Service;

@Service
public class ParticipacionService {

    private ParticipacionPort participacionPort;

    public Participacion insertarParticipacion(Participacion participacion) {
        return participacionPort.save(participacion);
    }
}
