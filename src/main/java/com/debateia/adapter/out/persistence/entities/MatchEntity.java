package com.debateia.adapter.out.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_match")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatchEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "league_id", nullable = false)
    private LeagueEntity league;

    @ManyToOne
    @JoinColumn(name = "bot1_id", nullable = false)
    private BotEntity bot1;

    @ManyToOne
    @JoinColumn(name = "bot2_id", nullable = false)
    private BotEntity bot2;

    private String state;
    private Integer result;

    @Column(name = "roundNumber")
    private Integer roundNumber;

    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MessageEntity> messages = new ArrayList<>();
}
