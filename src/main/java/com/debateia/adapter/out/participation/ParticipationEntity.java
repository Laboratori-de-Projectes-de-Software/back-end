package com.debateia.adapter.out.participation;

import com.debateia.adapter.out.bot.BotEntity;
import com.debateia.adapter.out.league.LeagueEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_participation")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(ParticipationId.class) // Add this for composite key
public class ParticipationEntity {
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
    
    private Integer points; // Si no se setea manualmente se iniciliza a 0
    private Integer position;
    private Integer nWins; 
    private Integer nDraws;
    private Integer nLoses;
}