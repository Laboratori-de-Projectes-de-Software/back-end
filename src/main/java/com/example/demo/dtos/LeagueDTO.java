package com.example.demo.dtos;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class LeagueDTO {
    private Integer id;
    private String name;
    private String urlImagen;
    private Integer rounds;
    private Long matchTime;
    private Long[] bots;
}
