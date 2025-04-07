package com.adondeband.back_end_adonde_band.API.bot;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BotDTOMin {

    private String name;

    private String description;

    private String urlImagen; // luego quizá sea ImagenDTO

    private String endpoint;

    public BotDTOMin(BotDTOResponse botDTO) {
        this.name = botDTO.getName();
        this.description = botDTO.getDescription();
        this.urlImagen = botDTO.getUrlImage();
        this.endpoint = botDTO.getEndpoint();
    }
}
