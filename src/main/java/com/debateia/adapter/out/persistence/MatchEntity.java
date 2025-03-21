package com.debateia.adapter.out.persistence;

import com.debateia.domain.Match;
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

    @Column(nullable = false)
    private Boolean enabled;

    // mappedBy mapea a "match" como atributo de CombatEntity
    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CombatEntity> combats = new ArrayList<>();
    
    public MatchEntity() {}
    
    // Convierte una clase Match del dominio a una clase MatchEntity (JPA)
    public MatchEntity(Match dom) {
        
    }
    
    // Convierte una clase MatchEntity (JPA) a una clase Match, del dominio de la aplicacion
    public Match toDomain() {
        return null;
    }

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

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<CombatEntity> getCombats() {
        return combats;
    }

    public void setCombats(List<CombatEntity> combats) {
        this.combats = combats;
    }
}
