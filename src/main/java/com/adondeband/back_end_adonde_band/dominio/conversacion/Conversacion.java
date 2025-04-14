package com.adondeband.back_end_adonde_band.dominio.conversacion;

import com.adondeband.back_end_adonde_band.dominio.enfrentamiento.Enfrentamiento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Conversacion {

    private Long id;

    private String ficheroRuta;

    private Enfrentamiento enfrentamiento;
}
