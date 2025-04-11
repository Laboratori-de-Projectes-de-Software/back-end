package com.example.back_end_eing.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BotResponseDTO {
    private final Long botId;
    private final String name;
    private final String description;
    private final String urlImage;
    @JsonProperty("nWins")
    private final Integer nWins;
    @JsonProperty("nLosses")
    private final Integer nLosses;
    @JsonProperty("nDraws")
    private final Integer nDraws;
}


