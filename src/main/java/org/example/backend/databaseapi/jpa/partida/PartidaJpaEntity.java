package org.example.backend.databaseapi.jpa.partida;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import org.example.backend.databaseapi.domain.Estado;
import org.example.backend.databaseapi.jpa.liga.LigaJpaEntity;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import java.time.Instant;
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

    @ManyToOne(optional = false)
    @JoinColumn(name="id_liga")
    private LigaJpaEntity liga;

    @Column(name="duracion_total")
    private Integer duracionTotal;

    private LocalDateTime fecha;

}
