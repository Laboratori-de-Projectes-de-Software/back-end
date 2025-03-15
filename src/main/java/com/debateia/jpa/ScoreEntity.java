package com.debateia.jpa;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_score")
public class ScoreEntity {
    @EmbeddedId // id compuesta
    private ScoreEntityId id;

    public ScoreEntityId getId() {
        return id;
    }

    public void setId(ScoreEntityId id) {
        this.id = id;
    }

    public AIEntity getAi() {
        return ai;
    }

    public void setAi(AIEntity ai) {
        this.ai = ai;
    }

    public CombatEntity getCombat() {
        return combat;
    }

    public void setCombat(CombatEntity combat) {
        this.combat = combat;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    @ManyToOne
    @MapsId("aiId") // mapeo al aiId de la clave compuesta de ScoreId
    @JoinColumn(name = "ai_id", nullable = false) // mapeo de clave foranea ai_id a clave primaria de AIEntity
    private AIEntity ai;

    @ManyToOne
    @MapsId("combatId") // mapeo al combatId de la clave compuesta de ScoreId
    @JoinColumn(name = "combat_id", nullable = false) // mapeo de clave foranea combat_id a clave primaria de CombatEntity
    private CombatEntity combat;

    @Column(nullable = false)
    private Integer points;
}
