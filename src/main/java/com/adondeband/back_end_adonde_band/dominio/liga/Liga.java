package com.adondeband.back_end_adonde_band.dominio.liga;

import com.adondeband.back_end_adonde_band.dominio.participacion.ParticipacionId;
import com.adondeband.back_end_adonde_band.dominio.estado.ESTADO;
import com.adondeband.back_end_adonde_band.dominio.imagen.Imagen;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
