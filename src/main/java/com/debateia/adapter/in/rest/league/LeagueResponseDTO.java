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
    private int id;
    private String name;
    private String imageUrl;
    private Integer rounds;
    private long matchMaxMessages;
    private List<Integer> bots;
    private State state;
}
