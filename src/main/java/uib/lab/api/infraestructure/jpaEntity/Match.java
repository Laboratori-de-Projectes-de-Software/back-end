package uib.lab.api.infraestructure.jpaEntity;

import lombok.*;

import java.util.Set;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "matches")
public class Match {

    public enum MatchState {
        PENDING, IN_PROGRESS, COMPLETED;
    }

    public enum MatchResult{
        LOCAL, VISITING, DRAW;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private MatchState state;

    @Enumerated(EnumType.STRING)
    private MatchResult result;

    private int rounds;

    @ManyToOne
    @JoinColumn(name = "bot_id1")
    private Bot bot1;

    @ManyToOne
    @JoinColumn(name = "bot_id2")
    private Bot bot2;

    @ManyToOne
    @JoinColumn(name = "round_id")
    private Round round;

    @OneToMany(mappedBy = "match")
    private Set<Chat> chat;
}


