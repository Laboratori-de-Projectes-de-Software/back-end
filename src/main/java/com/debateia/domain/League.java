package com.debateia.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class League {
    
    private int leagueId;
    private String name;
    private String urlImagen;
    private int rounds;
    private long matchTime;
    
    private List<Match> matches;
    private List<Integer> bots;
    private String state;
    private User user;
}
