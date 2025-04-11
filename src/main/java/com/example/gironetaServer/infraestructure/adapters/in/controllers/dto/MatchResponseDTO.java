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
    private Result result;
    private String[] fighters;
    private int roundNumber;
    
    // Enum para el resultado de la pelea
    public enum Result {
        LOCAL, VISITANT, DRAW
    }
}