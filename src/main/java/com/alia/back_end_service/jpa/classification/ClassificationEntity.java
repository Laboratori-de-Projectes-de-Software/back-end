package com.alia.back_end_service.jpa.classification;

import com.alia.back_end_service.jpa.bot.BotEntity;
import com.alia.back_end_service.jpa.round.RoundEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name ="classifications")
public class ClassificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer points;

    @Column(name = "number_matchs")
    private Integer numberMatchs;

    @Column(name = "win_matchs")
    private Integer winMatchs;

    @Column(name = "tie_matchs")
    private Integer tieMatchs;

    @Column(name = "lose_matchs")
    private Integer loseMatchs;

    @ManyToOne
    @JoinColumn(name = "bot_name", nullable = false)
    private BotEntity bot;

    @ManyToOne
    @JoinColumn(name = "round_id", nullable = false)
    private RoundEntity round;
}