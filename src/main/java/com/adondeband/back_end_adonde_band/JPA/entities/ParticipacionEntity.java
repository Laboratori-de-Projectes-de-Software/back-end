package com.adondeband.back_end_adonde_band.JPA.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class ParticipacionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "bot_id")
    private BotEntity bot;

    @ManyToOne
    @JoinColumn(name = "liga_id")
    private LigaEntity liga;

    private int posicion;
    private int puntuacion;
}
