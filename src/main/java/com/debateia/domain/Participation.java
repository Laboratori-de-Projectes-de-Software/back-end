package com.debateia.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Participation {
    private Integer leagueId;
    private Integer botId;
    private Integer points;
    private Integer position;
    private String name;
    private int nWins;
    private int nDraws;
    private int nLoses;
}
