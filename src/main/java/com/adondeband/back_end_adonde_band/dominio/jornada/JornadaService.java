package com.adondeband.back_end_adonde_band.dominio.jornada;

import com.adondeband.back_end_adonde_band.dominio.liga.Liga;
import com.adondeband.back_end_adonde_band.dominio.liga.LigaId;
import com.adondeband.back_end_adonde_band.dominio.usuario.UsuarioId;

import java.util.List;

public interface JornadaService {
    Jornada crearJornada(Jornada jornada);

    List<Jornada> obtenerJornadaPorId(JornadaId id);
}
