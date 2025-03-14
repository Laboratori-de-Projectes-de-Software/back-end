package com.adondeband.back_end_adonde_band.dominio.enfrentamiento;

import org.springframework.stereotype.Service;

@Service
public class EnfrentamientoImpl implements EnfrentamientoService {

    private EnfrentamientoPort enfrentamientoPort;

    public Enfrentamiento insertarEnfrentamiento(Enfrentamiento enfrentamiento) {
        return enfrentamientoPort.save(enfrentamiento);
    }
}
