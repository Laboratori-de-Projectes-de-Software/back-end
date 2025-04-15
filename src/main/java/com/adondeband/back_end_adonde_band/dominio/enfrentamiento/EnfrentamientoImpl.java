package com.adondeband.back_end_adonde_band.dominio.enfrentamiento;

import com.adondeband.back_end_adonde_band.dominio.conversacion.Conversacion;
import com.adondeband.back_end_adonde_band.dominio.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnfrentamientoImpl implements EnfrentamientoService {

    private final EnfrentamientoPort enfrentamientoPort;

    public EnfrentamientoImpl(EnfrentamientoPort enfrentamientoPort) {
        this.enfrentamientoPort = enfrentamientoPort;
    }

    @Override
    public Enfrentamiento insertarEnfrentamiento(Enfrentamiento enfrentamiento) {
        return enfrentamientoPort.save(enfrentamiento);
    }

    @Override
    public List<Enfrentamiento> obtenerEnfrentamiento(Long idPartido) {
        return enfrentamientoPort.findById(idPartido);
    }

    @Override
    public Enfrentamiento actualizarConversacion(EnfrentamientoId enfrentamientoId, Conversacion conversacion) {
        if (obtenerEnfrentamiento(enfrentamientoId.value()).isEmpty()) {
            throw new NotFoundException("Este enfrentamiento no existe");
        }
        return enfrentamientoPort.actualizarConversacion(enfrentamientoId, conversacion);
    }
}
