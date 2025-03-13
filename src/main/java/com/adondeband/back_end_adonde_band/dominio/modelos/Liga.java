package com.adondeband.back_end_adonde_band.dominio.modelos;

import com.adondeband.back_end_adonde_band.dominio.Ids.LigaId;
import com.adondeband.back_end_adonde_band.dominio.Ids.ParticipacionId;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class Liga {

    private LigaId id;

    private String nombre;

    private ESTADO estado;

    private LocalDateTime fechaInicio;

    private LocalDateTime fechaFin;

    private Imagen imagen;

    private List<ParticipacionId> participaciones;

    // private List<Jornada> jornadas;
}
