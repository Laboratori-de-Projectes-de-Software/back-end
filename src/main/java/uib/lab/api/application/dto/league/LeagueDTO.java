package uib.lab.api.application.dto.league;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LeagueDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String urlImagen;

    @NotNull
    @Min(1)
    private int matchTime;

    @NotNull
    @Min(1)
    private int rounds;

    @NotNull
    private int userId;

    private int[] bots;
}