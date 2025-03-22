package com.example.back_end_eing.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Entity
@Table(name = "bot")
public class Bot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nombreBot;

    @Column
    private String descripcionBot;

    @Column
    private String fotoBot;

    @Column
    private Integer numVictorias;

    @Column
    private Integer numJornadas;

    @Column
    private String api_key;

    public Bot() {

    }
}
