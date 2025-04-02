package com.alia.back_end_service.jpa.round;



import com.alia.back_end_service.jpa.classification.ClassificationEntity;
import com.alia.back_end_service.jpa.game.GameEntity;
import com.alia.back_end_service.jpa.league.LeagueEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "round")
public class RoundEntity {

    @Id
    @Column(nullable = false, unique = true, name = "game_id")
    private Integer id;

    @Column(nullable = false, name = "number_round")
    private Integer number_round;

    @Column(nullable = false, name = "init_time")
    private OffsetDateTime init_time;

    @Column(nullable = false, name = "end_time")
    private OffsetDateTime end_time;

    @Column(nullable = false, name = "state")
    private String state;;

    @ManyToOne
    @JoinColumn(name = "league_id", nullable = false)
    private LeagueEntity league;

    @OneToMany(mappedBy = "round", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<GameEntity> games;

    @OneToMany(mappedBy = "round", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ClassificationEntity> classifications;

}
