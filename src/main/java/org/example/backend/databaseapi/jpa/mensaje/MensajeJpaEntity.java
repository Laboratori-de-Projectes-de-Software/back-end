package org.example.backend.databaseapi.jpa.mensaje;

import jakarta.persistence.*;
import lombok.*;
import org.example.backend.databaseapi.jpa.bot.BotJpaEntity;
import org.example.backend.databaseapi.jpa.partida.PartidaJpaEntity;
import org.example.backend.databaseapi.jpa.resultado.ResultadoId;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name="mensaje")
public class MensajeJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_mensaje")
    private Integer mensajeId;

    @ManyToOne
    @JoinColumn(name="id_bot")
    private BotJpaEntity bot;

    @ManyToOne
    @JoinColumn(name="id_partida")
    private PartidaJpaEntity partida;

    private String texto; //Notnull

    @CreationTimestamp
    private Timestamp hora; //Notnull

}
