package com.adondeband.back_end_adonde_band.dominio.jornada;

import com.adondeband.back_end_adonde_band.dominio.liga.Liga;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class JornadaImpl implements JornadaService{
    private final JornadaPort jornadaPort;

    public JornadaImpl(JornadaPort jornadaPort) {
        this.jornadaPort = jornadaPort;
    }

    @Override
    public Jornada crearJornada(Jornada jornada) {
        return jornadaPort.save(jornada);
    }

    @Override
    public List<Jornada> obtenerJornadaPorId(JornadaId id) {
        return jornadaPort.findById(id.value());
    }
}
