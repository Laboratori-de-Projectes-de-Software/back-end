package com.debateia.domain;

import com.debateia.adapter.in.rest.league.State;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Match {
    private Integer matchId;
    private Integer leagueId;
    private List<Integer> messageIds;
    private Integer bot1id;
    private Integer bot2id;
    private State state;
    private Integer result;
    private Integer roundNumber;
    
    private List<String> fighters; // nombre de los bots (bot.name)

}
