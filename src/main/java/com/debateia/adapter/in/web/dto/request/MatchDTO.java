package com.debateia.adapter.in.web.dto.request;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@JsonTypeName("LeagueDTO")
public class MatchDTO {
    private String name;
    private String urlImagen;
    private Integer rounds;
    private long matchTime;
    private List<Integer> bots;
    private int userId;
}
