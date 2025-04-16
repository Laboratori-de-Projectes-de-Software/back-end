package jaumesitos.backend.demo.infrastructure.res.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ParticipationResponseDTO {
    Integer botId;
    String name;
    Integer points;
    Integer position;
    Integer nWins;
    Integer nDraws;
    Integer nLosses;

    public Integer getnWins() { return nWins; }
    public Integer getnDraws() { return nDraws; }
    public Integer getnLosses() { return nLosses; }

    public void setnWins(Integer nWins) { this.nWins = nWins; }
    public void setnDraws(Integer nDraws) { this.nDraws = nDraws; }
    public void setnLosses(Integer nLosses) { this.nLosses = nLosses; }

}
