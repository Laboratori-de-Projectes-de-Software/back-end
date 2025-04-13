package com.example.back_end_eing.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LeagueDTO {
    private String name;
    private String urlImagen;
    private Integer rounds;
    private long matchTime;
    private int[] bots;
    private int creador;


    public LeagueDTO (String name, String urlImagen, Integer rounds, long matchTime, int numBots, int userId){
        this.name = name;
        this.urlImagen = urlImagen;
        this.rounds = rounds;
        this.matchTime = matchTime;
        this.bots = new int[numBots];
        this.creador = userId;
    }
}
