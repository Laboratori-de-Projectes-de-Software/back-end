package com.example.demo.adapters.out.persistence.League;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * Entidad JPA que mapea la tabla "leagues" en la base de datos.
 */
@Table(name = "leagues")
@Entity
@Setter
@Getter
@Accessors(chain = true)
public class LeagueEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(unique = true, length = 100, nullable = false)
    private String urlImagen;

    @Column(nullable = false)
    private Integer rounds;

    @Column(nullable = false)
    private Long matchTime;

    @Column(nullable = false)
    private Long[] bots;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
