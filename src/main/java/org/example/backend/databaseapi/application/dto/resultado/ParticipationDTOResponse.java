package org.example.backend.databaseapi.application.dto.resultado;

import lombok.*;


@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class ParticipationDTOResponse {
    private Integer botId;
    private String name;
    private Integer points;
    private Integer position;
    private Integer nWins;
    private Integer nLosses;
    private Integer nDraws;
}
