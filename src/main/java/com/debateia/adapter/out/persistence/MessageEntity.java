package com.debateia.adapter.out.persistence;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "tbl_message")
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String contents;

    @Column(nullable = false)
    private Date timestamp;

    @ManyToOne
    @JoinColumn(name = "ai_id", nullable = false) // clave foranea ai_id al id de AIEntity
    private AIEntity ai;

    @ManyToOne
    @JoinColumn(name = "combat_id", nullable = false) // clave foranea combat_id al id de CombatEntity
    private CombatEntity combat;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
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

}
