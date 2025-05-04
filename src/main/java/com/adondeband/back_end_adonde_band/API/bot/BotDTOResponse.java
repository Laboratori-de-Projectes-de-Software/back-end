package com.adondeband.back_end_adonde_band.API.bot;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BotDTOResponse {

    private Integer id;

    private String name;

    private String quality;

    private String imageUrl; // luego quizá sea ImagenDTO

    private String apiUrl;

    @JsonProperty("nWins") // Asegura que el nombre en JSON sea "nWins" en lugar de "nwins"
    private Integer nWins;

    @JsonProperty("nDraws")
    private Integer nDraws;

    @JsonProperty("nLosses")
    private Integer nLosses;

    public BotDTOResponse(BotDTOMin botDTOMin) {
        this.name = botDTOMin.getName();
        this.quality = botDTOMin.getQuality();
        this.imageUrl = botDTOMin.getImageUrl();
        this.apiUrl = botDTOMin.getApiUrl();
        this.id = null; // botId se asigna en el controlador

        // default de otros atributos
        nLosses = nDraws = nWins = 0;
    }
}
