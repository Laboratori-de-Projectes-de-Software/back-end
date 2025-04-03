package com.adondeband.back_end_adonde_band.dominio.imagen;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Imagen {
    private int id;

    private String ruta;

    public Imagen(String ruta){
        this.ruta = ruta;
    }
}
