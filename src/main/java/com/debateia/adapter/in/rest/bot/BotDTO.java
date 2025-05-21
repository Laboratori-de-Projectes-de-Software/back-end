package com.debateia.adapter.in.rest.bot;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class BotDTO implements Serializable {
    private int id;
    private String name;
    private String quality;
    private String imageUrl;
    private String apiUrl;
    private int nWins;
    private int nLosses;
    private int nDraws;
}
