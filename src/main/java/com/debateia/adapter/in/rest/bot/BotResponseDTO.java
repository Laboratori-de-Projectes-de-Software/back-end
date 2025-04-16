package com.debateia.adapter.in.rest.bot;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class BotResponseDTO implements Serializable {
    private int botId;
    private String name;
    private String description;
    private String urlImage;
    private int nWins;
    private int nLosses;
    private int nDraws;
}
