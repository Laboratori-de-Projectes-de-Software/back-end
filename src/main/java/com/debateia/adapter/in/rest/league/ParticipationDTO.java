package com.debateia.adapter.in.rest.league;

import com.fasterxml.jackson.annotation.JsonTypeName;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author kjorda
 */

@NoArgsConstructor
@Data
@JsonTypeName("ParticipationDTO")
public class ParticipationDTO implements Serializable {
    private int botId;
    private String botName;
    private int points;
    private int position;
    private int nWins;
    private int nDraws;
    private int nLoses;
}
