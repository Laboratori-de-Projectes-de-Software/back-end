package com.example.back_end_eing.dto;

import com.example.back_end_eing.models.Bot;
import com.example.back_end_eing.models.Liga;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LeagueResponseDTO {
    private Integer leagueId;
    private String status;
    private String name;
    private String user; //owner
    private String urlImagen;
    private Integer rounds;
    private Long matchTime;
    private List<Integer> bots;


    public LeagueResponseDTO(Liga liga, List<Integer> bots) {

        this.leagueId = Math.toIntExact(liga.getId());
        this.status = liga.getEstado();
        this.name = liga.getNombreLiga();
        this.user = liga.getUsuario().getNombreUsuario();
        this.urlImagen = null;
        this.bots = bots;
        this.rounds = liga.getNumJornadas();
        this.matchTime = 120L;

    }
}