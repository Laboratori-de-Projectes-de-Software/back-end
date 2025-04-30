package org.example.backend.databaseapi.application.dto.liga;

import lombok.*;
import org.example.backend.databaseapi.domain.partida.Estado;

import java.util.List;

@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class LeagueDTOResponse {

    private Integer leagueId;
    private Estado estado;
    private String name;
    private String urlImagen;
    private Integer user;
    private Integer rounds;
    private Long matchTime;
    private List<Integer> bots;
}
