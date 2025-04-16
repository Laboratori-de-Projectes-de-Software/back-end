package com.example.gironetaServer.infraestructure.adapters.in.controllers.dto;

import com.example.gironetaServer.infraestructure.adapters.out.db.entities.EnfrentamientoEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MatchResponseDTO {

    private Long matchId;
    private EnfrentamientoEntity.State state;

    // Si es 0, es empate. Si es 1, el bot local gana. Si es 2, el bot visitante
    // gana.
    private int result;
    private BotResponseDTO[] fighters;
    private int roundNumber;
}