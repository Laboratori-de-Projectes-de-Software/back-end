package com.adondeband.back_end_adonde_band.jpa.participacion;

import com.adondeband.back_end_adonde_band.jpa.bot.BotEntity;
import com.adondeband.back_end_adonde_band.jpa.liga.LigaEntity;
import lombok.*;
import jakarta.persistence.*;



@Getter
@Setter
@Entity
public class ParticipacionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "bot_id")
    private BotEntity bot;

    @ManyToOne
    @JoinColumn(name = "liga_id")
    private LigaEntity liga;

    private int posicion;
    private int puntuacion;

    public ParticipacionEntity(Long id) {
        this.id = id;
    }

    public ParticipacionEntity() {

    }
}
