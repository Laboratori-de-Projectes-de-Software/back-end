package com.adondeband.back_end_adonde_band.API.bot;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BotDTOResponse {

    private Integer botId;

    private String name;

    private String description;

    private String urlImage; // luego quizá sea ImagenDTO

    private String endpoint;

    private Integer nWins;

    private Integer nDraws;

    private Integer nLosses;

    public BotDTOResponse(BotDTOMin botDTOMin) {
        this.name = botDTOMin.getName();
        this.description = botDTOMin.getDescription();
        this.urlImage = botDTOMin.getUrlImagen();
        this.endpoint = botDTOMin.getEndpoint();
        this.botId = null; // botId se asigna en el controlador

        // default de otros atributos
        nLosses = nDraws = nWins = 0;
    }
}
