package com.adondeband.back_end_adonde_band.API.liga;

import com.adondeband.back_end_adonde_band.dominio.estado.ESTADO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class LigaDTO {

    // Agregar setter
    private String nombre;
    // Agregar setter
    private ESTADO estado;
    // Agregar setter
    private LocalDateTime fechaInicio;
    // Agregar setter
    private LocalDateTime fechaFin;

    public LigaDTO(String nombre, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = ESTADO.PENDIENTE;
    }
}
