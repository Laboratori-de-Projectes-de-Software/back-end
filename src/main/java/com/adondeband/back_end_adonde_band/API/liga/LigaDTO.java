package com.adondeband.back_end_adonde_band.API.liga;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LigaDTO {

    private String name;
    private String imagen; // luego quizá se convierta en ImagenDTO
    private int rounds;
    private long matchTime;
    private List<Integer> bots;

    public LigaDTO(LigaResponseDTO ligaResponseDTO) {
        this.name = ligaResponseDTO.getNombre();
        this.imagen = ligaResponseDTO.getUrlImagen();
        this.rounds = ligaResponseDTO.getRondas();
        this.matchTime = ligaResponseDTO.getMatchTime();
        this.bots = ligaResponseDTO.getBots();
    }
}
