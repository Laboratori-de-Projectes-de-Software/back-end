package com.example.gironetaServer.infraestructure.adapters.in.controllers.dto;

import com.example.gironetaServer.infraestructure.adapters.out.db.entities.EnfrentamientoEntity;

public class MatchResponseDTO {

    private Long id;
    private EnfrentamientoEntity.State state;

    // Si es -1, es empate. Si es 0, el bot local gana. Si es 1, el bot visitante
    // gana.
    private int result;
    private BotResponseDTO[] fighters;
    private int roundNumber;

    public MatchResponseDTO() {
    }

    public MatchResponseDTO(Long id, EnfrentamientoEntity.State state, int result, BotResponseDTO[] fighters,
            int roundNumber) {
        this.id = id;
        this.state = state;
        this.result = result;
        this.fighters = fighters;
        this.roundNumber = roundNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EnfrentamientoEntity.State getState() {
        return state;
    }

    public void setState(EnfrentamientoEntity.State state) {
        this.state = state;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public BotResponseDTO[] getFighters() {
        return fighters;
    }

    public void setFighters(BotResponseDTO[] fighters) {
        this.fighters = fighters;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }
}