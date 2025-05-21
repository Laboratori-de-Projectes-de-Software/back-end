package com.debateia.adapter.in.rest.match;

import com.debateia.adapter.in.rest.bot.BotDTO;
import com.debateia.adapter.in.rest.league.State;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Data
@JsonTypeName("MatchDTO")
public class MatchDTO implements Serializable {
    private int id;
    private State state;
    private int result;
    private List<BotDTO> fighters; // nombre de los bots
    private int roundNumber;
}
