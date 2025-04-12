package org.example.backend.databaseapi.jpa.resultado;

import jakarta.persistence.*;
import lombok.*;
import org.example.backend.databaseapi.jpa.bot.BotJpaEntity;
import org.example.backend.databaseapi.jpa.partida.PartidaJpaEntity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name="resultado")
@IdClass(ResultadoIdJpa.class)
public class ResultadoJpaEntity {

    @Id
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="id_bot")
    private BotJpaEntity bot;

    @Id
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="id_partida")
    private PartidaJpaEntity partida;
    private Integer puntuacion;

}
