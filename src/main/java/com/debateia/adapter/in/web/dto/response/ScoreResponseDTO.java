package com.debateia.adapter.in.web.dto.response;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@Data
@JsonTypeName("ParticipationResponseDTO")
public class ScoreResponseDTO implements Serializable {
    private int botId;
    private String name;
    private int points;
    private int position;
}
