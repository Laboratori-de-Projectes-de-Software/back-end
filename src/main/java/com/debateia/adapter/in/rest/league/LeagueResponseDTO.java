package com.debateia.adapter.in.rest.league;

import com.debateia.adapter.in.rest.league.State;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Data
@JsonTypeName("LeagueResponseDTO")
public class LeagueResponseDTO implements Serializable {
    private int leagueId;
    private State state;
    private String name;
    private String urlImagen;
    private int user;
    private Integer rounds;
    private long matchTime;
    private List<Integer> bots;
}
