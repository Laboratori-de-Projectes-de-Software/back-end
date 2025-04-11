package com.debateia.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Match {
    private int matchId;
    private League league;
    private List<Messages> messages;
    private Bot bot1;
    private Bot bot2;
    private String state;
    private int result;
    private Integer roundNumber;
    
    private List<String> fighters; // nombre de los bots (bot.trait)
}
