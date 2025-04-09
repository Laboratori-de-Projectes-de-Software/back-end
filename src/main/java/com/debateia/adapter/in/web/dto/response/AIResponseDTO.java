package com.debateia.adapter.in.web.dto.response;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@Data
@JsonTypeName("BotResponseDTO")
public class AIResponseDTO implements Serializable {
    private int botId;
    private String name;
    private String description;
    private String urlImage;
    private int nWins;
    private int nLosses;
    private int nDraws;
}
