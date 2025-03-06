package com.adondeband.back_end_adonde_band.dominio.services;

import com.adondeband.back_end_adonde_band.dominio.modelos.Conversacion;
import com.adondeband.back_end_adonde_band.dominio.puertos.out.ConversacionPort;
import org.springframework.stereotype.Service;

@Service
public class ConversacionService {

    private ConversacionPort conversacionPort;

    public Conversacion insertarEnfrentamiento(Conversacion conversacion) {
        return conversacionPort.save(conversacion);
    }
}
