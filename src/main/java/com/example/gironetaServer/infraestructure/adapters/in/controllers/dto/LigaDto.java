package com.example.gironetaServer.infraestructure.adapters.in.controllers.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class LigaDto {
    private Long id;
    private String name;
    private String urlImagen;
    private Integer rounds;
    private Long matchTime;
    private List<Long> bots;

    public LigaDto() {

    }

    public LigaDto(Long id, String name, String urlImagen, Integer rounds, Long matchTime, List<Long> bots) {
        this.id = id;
        this.name = name;
        this.urlImagen = urlImagen;
        this.rounds = rounds;
        this.matchTime = matchTime;
        this.bots = bots;
    }
}
