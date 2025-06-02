package com.debateia.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bot {
    private Integer id;
    private String name;
    private String description;
    private String endpoint;
    private String urlImagen;
    private Integer userId;
    private Integer nWins;
    private Integer nDraws;
    private Integer nLosses;
}


