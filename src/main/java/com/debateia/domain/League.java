package com.debateia.domain;

import lombok.Data;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class League {
    
    private Integer leagueId;
    private String name;
    private String urlImagen;
    private Integer rounds;
    private Long matchMaxMessages;
    
    private List<Integer> matchIds;
    private List<Integer> botIds;
    private String state;
    private Integer userId;
}
