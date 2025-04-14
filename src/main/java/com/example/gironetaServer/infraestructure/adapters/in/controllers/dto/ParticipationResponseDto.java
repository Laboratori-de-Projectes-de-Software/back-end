package com.example.gironetaServer.infraestructure.adapters.in.controllers.dto;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ParticipationResponseDto {
    private Long botId;
    private String name;
    private int points;
    private int position;
    private int nWins;
    private int nDraws;
    private int nLosses;
}