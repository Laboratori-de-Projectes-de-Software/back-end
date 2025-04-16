package com.adondeband.back_end_adonde_band.API.bot;

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

    private Integer nWins;

    private Integer nDraws;

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
