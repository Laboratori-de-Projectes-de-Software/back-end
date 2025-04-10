package com.debateia.adapter.out.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_participation")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParticipationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "participation_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "league_id", nullable = false)
    private LeagueEntity league;

    @ManyToOne
    @JoinColumn(name = "bot_id", nullable = false)
    private BotEntity bot;

    private Integer points;
    private Integer position;
}
