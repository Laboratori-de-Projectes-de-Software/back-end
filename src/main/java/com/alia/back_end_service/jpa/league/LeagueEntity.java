package com.alia.back_end_service.jpa.league;

import com.alia.back_end_service.jpa.bot.BotEntity;
import com.alia.back_end_service.jpa.round.RoundEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "leagues")
@Getter
@Setter
public class LeagueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(name = "init_time", nullable = false)
    private OffsetDateTime init_time;

    @Column(name = "end_time")
    private OffsetDateTime end_time;

    @Column(name = "time_match", nullable = false)
    private Integer time_match;

    @Column(name = "number_match", nullable = false)
    private Integer number_match;

    @Column(nullable = false)
    private String state;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "league_bots",
            joinColumns = @JoinColumn(name = "league_id"),
            inverseJoinColumns = @JoinColumn(name = "bot_name")
    )
    private List<BotEntity> bots;

}
