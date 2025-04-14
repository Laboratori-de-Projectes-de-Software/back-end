package uib.lab.api.application.dto.league;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ParticipationResponseDTO {
    @NotNull
    private Integer botId;

    @NotBlank
    private String name;

    @NotNull
    @Min(0)
    private Integer points;

    @NotNull
    @Min(1)
    private Integer position;

    @NotNull
    @Min(0)
    private Integer nWins;

    @NotNull
    @Min(0)
    private Integer nDraws;

    @NotNull
    @Min(0)
    private Integer nLoses;
}
