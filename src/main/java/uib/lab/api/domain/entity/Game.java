package uib.lab.api.domain.entity;

import lombok.*;
import javax.persistence.*;

@Entity
@Getter
@Setter
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private GameState state;

    @ManyToOne
    @JoinColumn(name = "bot_id1")
    private Bot bot1;

    @ManyToOne
    @JoinColumn(name = "bot_id2")
    private Bot bot2;

    @ManyToOne
    @JoinColumn(name = "round_id")
    private Round round;
}

enum GameState {
    PENDING, IN_PROGRESS, COMPLETED;
}
