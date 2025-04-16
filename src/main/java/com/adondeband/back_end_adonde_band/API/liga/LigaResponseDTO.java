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
    private Long leagueId;
    private String name;
    private ESTADO state;
    private String urlImagen; // luego quizá se convierta en ImagenDTO
    private List<Long> bots;
    private Integer rounds;
    private Long matchTime;

    public LigaResponseDTO(LigaDTO ligaDTO) {
        this.name = ligaDTO.getName();
        this.urlImagen = ligaDTO.getUrlImagen();
        this.rounds = ligaDTO.getRounds();
        this.matchTime = ligaDTO.getMatchTime();
        this.bots = null;               // al crear la liga no tiene bots
        this.leagueId = null;                 // ligaId se asigna en el controlador
        this.state = ESTADO.PENDIENTE; // default de otros atributos
    }
}