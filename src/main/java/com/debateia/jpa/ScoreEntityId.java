package com.debateia.jpa;

import jakarta.persistence.Embeddable;

@Embeddable // definicion de clave principal compuesta en la BD
public class ScoreEntityId {
    private Integer aiId;

    public ScoreEntityId(Integer aiId, Integer combatId) {
        this.aiId = aiId;
        this.combatId = combatId;
    }

    public void setAiId(Integer aiId) {
        this.aiId = aiId;
    }

    public Integer getAiId() {
        return aiId;
    }

    private Integer combatId;
}
