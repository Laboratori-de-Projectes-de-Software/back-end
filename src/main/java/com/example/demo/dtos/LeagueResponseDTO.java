package com.example.demo.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LeagueResponseDTO {
    private Integer leagueId;
    private String state;
    private String name;
    private String urlImagen;
    private Integer user;
    private Integer rounds;
    private Long matchTime;
    private Long[] bots;
}
