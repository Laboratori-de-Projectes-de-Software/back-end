package com.debateia.adapter.out.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_league_bots")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(LeagueBotsId.class) // Add this for composite key
public class LeagueBotsEntity {
    
    @Id
    @Column(name = "league_id")
    private Integer leagueId;
    
    @Id
    @Column(name = "bot_id")
    private Integer botId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "league_id", insertable = false, updatable = false)
    private LeagueEntity league;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bot_id", insertable = false, updatable = false)
    private BotEntity bot;
}