package com.adondeband.back_end_adonde_band.jpa.jornada;

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
    private long id;

    private int numJornada;

    @ManyToOne
    private LigaEntity liga;
}
