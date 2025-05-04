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

    private String quality;

    private String imageUrl; // luego quizá sea ImagenDTO

    private String apiUrl;

    public BotDTOMin(BotDTOResponse botDTO) {
        this.name = botDTO.getName();
        this.quality = botDTO.getQuality();
        this.imageUrl = botDTO.getImageUrl();
        this.apiUrl = botDTO.getApiUrl();
    }
}
