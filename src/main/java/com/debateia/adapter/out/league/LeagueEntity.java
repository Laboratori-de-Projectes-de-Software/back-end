package com.debateia.adapter.out.league;

import com.debateia.adapter.out.participation.ParticipationEntity;
import com.debateia.adapter.out.match.MatchEntity;
import com.debateia.adapter.out.user.UserEntity;
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
    private String urlImagen;

    private String state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    private Integer rounds;

    @Column(name = "match_time")
    private Long matchMaxMessages;

    @OneToMany(mappedBy = "league", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MatchEntity> matches = new ArrayList<>();
    
    @OneToMany(mappedBy = "league", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ParticipationEntity> participations = new ArrayList<>();
}
