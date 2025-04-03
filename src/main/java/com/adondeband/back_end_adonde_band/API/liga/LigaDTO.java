package com.adondeband.back_end_adonde_band.API.liga;

import com.adondeband.back_end_adonde_band.API.imagen.ImagenDTO;
import com.adondeband.back_end_adonde_band.dominio.estado.ESTADO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LigaDTO {

    private String nombre;
    private String urlImagen; // luego quizá se convierta en ImagenDTO
    private ESTADO estado;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;

    public LigaDTO(String nombre, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        this.nombre = nombre;
        urlImagen = "";
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = ESTADO.PENDIENTE;
    }
}
