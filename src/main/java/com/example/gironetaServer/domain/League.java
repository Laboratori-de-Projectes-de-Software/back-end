package com.example.gironetaServer.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Builder
@Getter
@Setter
public class League {
    private Long id;
    private String name;
    private String urlImagen;
    private Integer rounds;
    private Long matchTime;
    private List<Long> bots;
    private String userId;

    public League() {
    }

    public League(Long id, String name, String urlImagen, Integer rounds, Long matchTime, List<Long> bots,
            String userId) {
        this.id = id;
        this.name = name;
        this.urlImagen = urlImagen;
        this.rounds = rounds;
        this.matchTime = matchTime;
        this.bots = bots;
        this.userId = userId;
    }
}