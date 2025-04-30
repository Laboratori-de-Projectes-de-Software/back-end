package org.example.backend.databaseapi.application.dto.partida;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class MatchDTOResponse {

    private Integer matchId;
    private String state;
    private Result result;
    private List<String> fighters;
    private Integer roundNumber;
}
