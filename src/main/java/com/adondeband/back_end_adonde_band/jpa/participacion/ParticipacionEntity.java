package com.adondeband.back_end_adonde_band.jpa.participacion;

import com.adondeband.back_end_adonde_band.jpa.bot.BotEntity;
import com.adondeband.back_end_adonde_band.jpa.liga.LigaEntity;
import lombok.*;
import jakarta.persistence.*;



@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"bot_id", "liga_id"})
        }
)
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

    private Integer posicion = 0;

    private Integer puntuacion = 0;

    private Integer numVictorias = 0;

    private Integer numEmpates = 0;

    private Integer numDerrotas = 0;

    public ParticipacionEntity(Long id) {
        this.id = id;
    }

    public ParticipacionEntity(BotEntity bot, LigaEntity liga) {
        this.bot = bot;
        this.liga = liga;
    }
}
