package uib.lab.api.application.dto.league;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter
@Setter
public class LeagueResponseDTO {
    public LeagueResponseDTO(int leagueId, String state, String name, String urlImagen, int matchTime, int rounds, int user, int[] bots) {
        this.leagueId = leagueId;
        this.state = state;
        this.name = name;
        this.urlImagen = urlImagen;
        this.matchTime = matchTime;
        this.rounds = rounds;
        this.user = user;
        this.bots = bots;
    }

    @NotNull
    private int leagueId;

    @NotBlank
    private String state;

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
    private int user;

    private int[] bots;
}
