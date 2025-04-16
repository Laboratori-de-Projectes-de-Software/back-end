package com.example.back_end_eing.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BotResponseDTO {
    private  Long botId;
    private  String name;
    private  String description;
    private  String urlImage;
    private  Integer nWins;
    private  Integer nLosses;
    private  Integer nDraws;


    public BotResponseDTO(BotDTO bot, Long botId, String urlImage) {
        this.botId = botId;
        this.name = bot.getName();
        this.description = bot.getDescription();
        this.urlImage = urlImage;
        this.nWins = 0;
        this.nLosses = 0;
        this.nDraws = 0;
    }
}


