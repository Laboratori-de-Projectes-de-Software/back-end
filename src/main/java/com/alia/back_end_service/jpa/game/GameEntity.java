package com.alia.back_end_service.jpa.game;

import com.alia.back_end_service.jpa.bot.BotEntity;
import com.alia.back_end_service.jpa.message.MessageEntity;
import com.alia.back_end_service.jpa.round.RoundEntity;
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
@Table(name = "game")
public class GameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    private Integer id;

    @Column(name = "duration")
    private OffsetDateTime timestamp;

    @Column(name = "result_local") //Redundant
    private String result_local;

    @Column(name = "result_visit") //Redundant
    private String result_visit;

    @ManyToOne
    @JoinColumn(name = "localBot")
    private BotEntity localBot;

    @ManyToOne
    @JoinColumn(name = "visitorBot")
    private BotEntity visitorBot;

    @ManyToOne
    @JoinColumn(name = "round_id")
    private RoundEntity round;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<MessageEntity> messages = new ArrayList<>();

    @Column(name = "state")
    private String state;

}
