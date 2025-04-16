package jaumesitos.backend.demo.infrastructure.res.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LeagueResponseDTO {
    private Integer leagueId;
    private Integer matchTime;
    private String name;
    private Integer rounds;
    private String urlImagen;
    private String state;
    private Integer user;
    private Integer [] bots;
}
