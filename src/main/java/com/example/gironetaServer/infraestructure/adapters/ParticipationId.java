package com.example.gironetaServer.infraestructure.adapters;

import java.io.Serializable;
import java.util.Objects;

public class ParticipationId implements Serializable {
    private Long bot;  // Debe coincidir con el nombre de los atributos PK en ParticipacionEntity
    private Long league;

    // Constructor vacío
    public ParticipationId() {}

    // Constructor
    public ParticipationId(Long bot, Long liga) {
        this.bot = bot;
        this.league = liga;
    }

    // equals() y hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParticipationId)) return false;
        ParticipationId that = (ParticipationId) o;
        return Objects.equals(bot, that.bot) && Objects.equals(league, that.league);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bot, league);
    }
}
