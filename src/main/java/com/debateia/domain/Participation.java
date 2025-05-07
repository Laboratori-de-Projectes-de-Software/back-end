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
    private Integer nWins;
    private Integer nDraws;
    private Integer nLoses;
}
