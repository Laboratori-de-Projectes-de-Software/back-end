package com.debateia.adapter.in.web.dto.response;

import com.debateia.adapter.in.web.dto.State;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@RequiredArgsConstructor
@Getter
@Setter
@JsonTypeName("LeagueResponseDTO")
public class MatchResponseDTO {
    private int leagueId;
    private State state;
    private String name;
    private String urlImagen;
    private int user;
    private Integer rounds;
    private long matchTime;
    private List<Integer> bots;
}
