package com.adondeband.back_end_adonde_band.dominio.services;

import com.adondeband.back_end_adonde_band.dominio.modelos.Enfrentamiento;
import com.adondeband.back_end_adonde_band.dominio.puertos.in.EnfrentamientoService;
import com.adondeband.back_end_adonde_band.dominio.puertos.out.EnfrentamientoPort;
import org.springframework.stereotype.Service;

@Service
public class EnfrentamientoImpl implements EnfrentamientoService {

    private EnfrentamientoPort enfrentamientoPort;

    public Enfrentamiento insertarEnfrentamiento(Enfrentamiento enfrentamiento) {
        return enfrentamientoPort.save(enfrentamiento);
    }
}
