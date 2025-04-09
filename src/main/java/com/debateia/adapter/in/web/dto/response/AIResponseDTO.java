package com.debateia.adapter.in.web.dto.response;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
@JsonTypeName("BotResponseDTO")
public class AIResponseDTO {
    private int botId;
    private String name;
    private String description;
    private String urlImage;
    private int nWins;
    private int nLosses;
    private int nDraws;
}
