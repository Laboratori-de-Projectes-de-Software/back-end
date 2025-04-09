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
@JsonTypeName("MatchResponseDTO")
public class CombatResponseDTO {
    private int matchId;
    private State state;
    private int result;
    private List<String> fighters;
    private int roundNumber;
}
