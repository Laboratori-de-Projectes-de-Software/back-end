package uib.lab.api.application.dto.match;

import lombok.Getter;
import lombok.Setter;
import uib.lab.api.infraestructure.jpaEntity.Match;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;

@Getter
@Setter
public class MatchResponseDTO {

    public MatchResponseDTO(int matchId, Match.MatchState state, int result, int roundNumber, String[] fighters, Integer[] ids){
        this.matchId = matchId;
        this.state = state;
        this.result = result;
        this.roundNumber = roundNumber;
        this.fighters = fighters;
        this.ids = ids;
    }

    @NotNull
    @Min(1)
    private int matchId;
    
    @NotBlank
    private Match.MatchState state;

    @NotBlank
    private int result;

    @NotNull
    @Min(1)
    private int roundNumber;

    private String[] fighters;

    private Integer[] ids;
}
