package uib.lab.api.application.dto.league;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LeagueRequest {

    @NotBlank
    private String name;

    @NotNull
    @Min(1)
    private int playTime;

    @NotNull
    @Min(1)
    private int numRounds;
}