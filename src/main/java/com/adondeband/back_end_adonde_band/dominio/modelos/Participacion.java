package com.adondeband.back_end_adonde_band.dominio.modelos;

import com.adondeband.back_end_adonde_band.dominio.Ids.BotId;
import com.adondeband.back_end_adonde_band.dominio.Ids.LigaId;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Participacion {

    private long id;

    private BotId bot;

    private LigaId liga;

    private int posicion;
    private int puntuacion;
}
