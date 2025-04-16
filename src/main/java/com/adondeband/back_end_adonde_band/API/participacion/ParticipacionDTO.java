package com.adondeband.back_end_adonde_band.API.participacion;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("nWins") // Asegura que el nombre en JSON sea "nWins" en lugar de "nwins"
    private Integer nWins;

    @JsonProperty("nDraws")
    private Integer nDraws;

    @JsonProperty("nLosses")
    private Integer nLosses;
}
