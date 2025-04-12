package org.example.backend.databaseapi.jpa.partida;

import jakarta.persistence.*;
import lombok.*;

import org.example.backend.databaseapi.domain.partida.Estado;
import org.example.backend.databaseapi.jpa.liga.LigaJpaEntity;
import org.springframework.data.annotation.CreatedBy;


import java.time.LocalDateTime;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Table(name="partida")
public class PartidaJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @CreatedBy
    @Column(name="id_partida")
    private Integer partidaId;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    @Column(name="round_number")
    private Integer roundNumber;

    private Integer resultado;

    @ManyToOne(optional = false)
    @JoinColumn(name="id_liga")
    private LigaJpaEntity liga;

    @Column(name="duracion_total")
    private Long duracionTotal;

}
