package com.adondeband.back_end_adonde_band.API.DTO;

import com.adondeband.back_end_adonde_band.dominio.modelos.ESTADO;
import java.time.LocalDateTime;

public class LigaDTO {

    private String nombre;
    private ESTADO estado;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;

    public LigaDTO(String nombre, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = ESTADO.PENDIENTE;
    }
}
