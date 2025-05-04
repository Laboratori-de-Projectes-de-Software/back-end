package com.adondeband.back_end_adonde_band.dominio.participacion;

import org.springframework.stereotype.Service;

@Service
public class ParticipacionImpl implements ParticipacionService {

    private final ParticipacionPort participacionPort;


    public ParticipacionImpl(ParticipacionPort participacionPort) {
        this.participacionPort = participacionPort;
    }

    public Participacion insertarParticipacion(Participacion participacion) {
        return participacionPort.save(participacion);
    }

    public Participacion obtenerParticipacion(ParticipacionId id) {
        return participacionPort.findById(id);
    }
}
