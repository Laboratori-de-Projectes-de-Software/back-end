package com.adondeband.back_end_adonde_band.API.liga;

import com.adondeband.back_end_adonde_band.dominio.estado.ESTADO;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {  // Agregar setter
        this.nombre = nombre;
    }

    public ESTADO getEstado() {
        return estado;
    }

    public void setEstado(ESTADO estado) {  // Agregar setter
        this.estado = estado;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {  // Agregar setter
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDateTime fechaFin) {  // Agregar setter
        this.fechaFin = fechaFin;
    }
}
