package com.debateia.adapter.in.web.dto.response;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author kjorda
 */

@NoArgsConstructor
@Data
public class ParticipationResponseDTO implements Serializable {
    private int botId;
    private String name;
    private int points;
    private int position;
    private int nWins;
    private int nDraws;
    private int nLoses;
}
