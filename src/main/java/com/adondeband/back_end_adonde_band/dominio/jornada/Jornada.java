package com.adondeband.back_end_adonde_band.dominio.jornada;

import com.adondeband.back_end_adonde_band.dominio.enfrentamiento.EnfrentamientoId;
import com.adondeband.back_end_adonde_band.dominio.liga.LigaId;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class Jornada {

    private JornadaId id;

    private int numJornada;

    private LigaId liga;

    private List<EnfrentamientoId> enfrentamientos;
}
