package com.debateia.adapter.in.web.dto.response;

import com.debateia.adapter.in.web.dto.State;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Data
@JsonTypeName("MatchResponseDTO")
public class MatchResponseDTO implements Serializable {
    private int matchId;
    private State state;
    private int result;
    private List<String> fighters;
    private int roundNumber;
}
