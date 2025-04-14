package com.adondeband.back_end_adonde_band.dominio.conversacion;

import org.springframework.stereotype.Service;

@Service
public class ConversacionImpl implements ConversacionService {

    private ConversacionPort conversacionPort;

    public Conversacion insertarEnfrentamiento(Conversacion conversacion) {
        return conversacionPort.save(conversacion);
    }
}
