package com.debateia.jpa;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_combat")
public class CombatEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "match_id", nullable = false) // clave foranea a match
    private MatchEntity match;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MatchEntity getMatch() {
        return match;
    }

    public void setMatch(MatchEntity match) {
        this.match = match;
    }

    public List<MessageEntity> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageEntity> messages) {
        this.messages = messages;
    }

    // mappedBy mapea a "combat" como atributo de MessageEntity
    @OneToMany(mappedBy = "combat", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // relacion 1 combate -> * msgs
    private List<MessageEntity> messages = new ArrayList<>();

}
