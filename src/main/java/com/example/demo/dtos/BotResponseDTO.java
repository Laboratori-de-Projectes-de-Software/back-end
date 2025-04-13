package com.example.demo.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BotResponseDTO {
    private Integer botId;
    private String name;
    private String description;
    private String urlImagen;
    private Integer nWins;
    private Integer nLosses;
    private Integer nDraws;
}
