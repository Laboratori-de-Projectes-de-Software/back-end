package com.adondeband.back_end_adonde_band.API.liga;

import com.adondeband.back_end_adonde_band.dominio.estado.ESTADO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LigaResponseDTO {
    private Long id;
    private String nombre;
    private ESTADO estado;
    private String urlImagen; // luego quizá se convierta en ImagenDTO
    private List<Integer> bots;
    private int rondas;
    private long matchTime;

    public LigaResponseDTO(LigaDTO ligaDTO) {
        this.nombre = ligaDTO.getName();
        this.urlImagen = ligaDTO.getImagen();
        this.rondas = ligaDTO.getRounds();
        this.matchTime = ligaDTO.getMatchTime();
        this.bots = ligaDTO.getBots();
        this.id = null; // ligaId se asigna en el controlador
        this.estado = ESTADO.PENDIENTE; // default de otros atributos
    }
}