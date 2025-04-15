package com.adondeband.back_end_adonde_band.API.participacion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParticipacionDTO {
    // Atributos
    private Long botId;
    private String botName;
    private Integer points;
    private Integer position;
    private Integer nWins;
    private Integer nDraws;
    private Integer nLosses;
}
