package uib.lab.api.application.dto.league;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LeagueRequest {

    @NotBlank //(message = "El nombre de la liga es obligatorio")
    private String name;

    @NotNull //(message = "El tiempo de juego es obligatorio")
    private int playTime;

    @NotNull //(message = "El número de rondas es obligatorio")
    private int numRounds;
}
