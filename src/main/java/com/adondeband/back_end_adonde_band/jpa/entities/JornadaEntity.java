package com.adondeband.back_end_adonde_band.jpa.entities;

import com.adondeband.back_end_adonde_band.jpa.enfrentamiento.EnfrentamientoEntity;
import com.adondeband.back_end_adonde_band.jpa.liga.LigaEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
public class JornadaEntity {

    @Id
    @Column(unique=true, nullable = false)
    private int numJornada;

    @ManyToOne
    private LigaEntity liga;

    @OneToMany(mappedBy = "jornada")
    private List<EnfrentamientoEntity> enfrentamientos;
}
