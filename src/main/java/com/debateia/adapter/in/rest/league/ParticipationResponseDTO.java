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
@JsonTypeName("ParticipationResponseDTO")
public class ParticipationResponseDTO implements Serializable {
    private int botId;
    private String name;
    private int points;
    private int position;
    private int nWins;
    private int nDraws;
    private int nLoses;
}
