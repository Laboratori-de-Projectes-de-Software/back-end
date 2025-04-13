package com.example.demo.adapters.out.persistence.Bot;

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
@Table(name = "bots")
@Entity
@Setter
@Getter
@Accessors(chain = true)
public class BotEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String urlImagen;

    @Column(nullable = false)
    private String endpoint;

    @Column(nullable = false)
    private Integer nWins;

    @Column(nullable = false)
    private Integer nLosses;

    @Column(nullable = false)
    private Integer nDraws;

    @Column(nullable = true)
    private Integer userId;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
