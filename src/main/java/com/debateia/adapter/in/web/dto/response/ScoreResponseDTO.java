package com.debateia.adapter.in.web.dto.response;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Setter
@Getter
@JsonTypeName("ParticipationResponseDTO")
public class ScoreResponseDTO {
    private int botId;
    private String name;
    private int points;
    private int position;
}
