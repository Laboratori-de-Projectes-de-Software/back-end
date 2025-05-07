package jaumesitos.backend.demo.infrastructure.res.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LeagueDTO {
    private Integer matchTime;
    private String name;
    private Integer rounds;
    private String urlImagen;
    private String state;
    private Integer [] bots;
}
