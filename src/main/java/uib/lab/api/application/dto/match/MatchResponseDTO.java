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

    public MatchResponseDTO(int matchId, Match.MatchState state, Match.MatchResult result, int roundNumber, String[] fighters){
        this.matchId = matchId;
        this.state = state;
        this.result = result;
        this.roundNumber = roundNumber;
        this.fighters = fighters;
    }

    @NotNull
    @Min(1)
    private int matchId;
    
    @NotBlank
    private Match.MatchState state;

    @NotBlank
    private Match.MatchResult result;

    @NotNull
    @Min(1)
    private int roundNumber;

    private String[] fighters;
}
