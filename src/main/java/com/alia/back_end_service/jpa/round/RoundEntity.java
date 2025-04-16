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
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "round")
public class RoundEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    private Integer id;

    @Column(name = "number_round")
    private Integer number_round;

    @Column(name = "init_time")
    private OffsetDateTime init_time;

    @Column(name = "end_time")
    private OffsetDateTime end_time;

    @Column( name = "state")
    private String state;

    @ManyToOne
    @JoinColumn(name = "league_id")
    private LeagueEntity league;

//    @OneToMany(mappedBy = "round", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
//    private List<ClassificationEntity> classifications = new ArrayList<>();

}
