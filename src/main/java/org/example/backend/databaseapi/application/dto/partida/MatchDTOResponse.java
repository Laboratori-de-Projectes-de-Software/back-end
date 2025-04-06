package org.example.backend.databaseapi.application.dto.partida;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class MatchDTOResponse {

    private String state;
    private Integer result;
    private List<String> fighters;
    private Integer roundNumber;
}
