package com.adondeband.back_end_adonde_band.API.liga;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LigaDTO {

    private String name;
    private String imageUrl; // luego quizá se convierta en ImagenDTO
    private Integer rounds;
    private Long matchTime;

    public LigaDTO(LigaResponseDTO ligaResponseDTO) {
        this.name = ligaResponseDTO.getName();
        this.imageUrl = ligaResponseDTO.getImageUrl();
        this.rounds = ligaResponseDTO.getRounds();
        this.matchTime = ligaResponseDTO.getMatchTime();
    }
}
