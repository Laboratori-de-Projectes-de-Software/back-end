package uib.lab.api.infraestructure.jpaEntity;

import lombok.*;
import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "matches")
public class Match {

    public enum MatchState {
        PENDING, IN_PROGRESS, COMPLETED;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private MatchState state;

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