package com.adondeband.back_end_adonde_band.dominio.modelos;

import com.adondeband.back_end_adonde_band.dominio.Ids.EnfrentamientoId;
import com.adondeband.back_end_adonde_band.dominio.Ids.JornadaId;
import com.adondeband.back_end_adonde_band.dominio.Ids.LigaId;
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
