package com.debateia.adapter.out.persistence;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tbl_match")
public class MatchEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id auto incremento
    private Integer id;

    @Column(nullable = false)
    private Date date;

    // mappedBy mapea a "match" como atributo de CombatEntity
    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CombatEntity> combats = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<CombatEntity> getCombats() {
        return combats;
    }

    public void setCombats(List<CombatEntity> combats) {
        this.combats = combats;
    }
}
