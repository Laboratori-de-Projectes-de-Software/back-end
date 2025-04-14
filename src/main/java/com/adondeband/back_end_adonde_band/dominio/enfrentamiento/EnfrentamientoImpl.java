package com.adondeband.back_end_adonde_band.dominio.enfrentamiento;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnfrentamientoImpl implements EnfrentamientoService {

    private final EnfrentamientoPort enfrentamientoPort;

    public EnfrentamientoImpl(EnfrentamientoPort enfrentamientoPort) {
        this.enfrentamientoPort = enfrentamientoPort;
    }

    public Enfrentamiento insertarEnfrentamiento(Enfrentamiento enfrentamiento) {
        return enfrentamientoPort.save(enfrentamiento);
    }

    public List<Enfrentamiento> obtenerEnfrentamiento(Long idPartido) {
        return enfrentamientoPort.findById(idPartido);
    }
}
