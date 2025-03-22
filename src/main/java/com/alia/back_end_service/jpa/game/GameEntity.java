package com.alia.back_end_service.jpa.game;

import com.alia.back_end_service.jpa.bot.BotEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.OffsetDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "game")
public class GameEntity {

    @Id
    @Column(nullable = false, unique = true, name = "game_id")
    private Integer id;

    @Column(nullable = false, name = "duration")
    private OffsetDateTime timestamp;

    @Column(nullable = true, unique = false, name = "result_local") //Redundant
    private String result_local;

    @Column(nullable = true, unique = false, name = "result_visit") //Redundant
    private String result_visit;

    @ManyToOne
    @JoinColumn(name = "local_bot_id", nullable = false)
    private BotEntity bot_local_id;

    @ManyToOne
    @JoinColumn(name = "visit_bot_id", nullable = false)
    private BotEntity bot_visit_id;

    //Posible relación con mensaje

    //Posible relación con jornada

    @Column(name = "state")
    private String state;

}
