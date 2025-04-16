package com.debateia.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
    private int nWins;
    private int nDraws;
    private int nLosses;
}


