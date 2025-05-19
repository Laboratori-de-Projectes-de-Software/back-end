package com.debateia.adapter.in.rest.bot;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class BotResponseDTO implements Serializable {
    private int id;
    private String name;
    private String quality;
    private String imageUrl;
    private String apiUrl;
    private int nWins;
    private int nLosses;
    private int nDraws;
}
