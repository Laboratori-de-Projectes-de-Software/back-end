package com.debateia.adapter.out.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_league")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeagueEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "league_id")
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(name = "urlImagen")
    private String urlImage;

    private String state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    private int rounds;

    @Column(name = "match_time")
    private Long matchTime;

    @OneToMany(mappedBy = "league", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MatchEntity> matches = new ArrayList<>();
}
