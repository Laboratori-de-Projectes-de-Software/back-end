package uib.lab.api.domain.entity;

import lombok.*;
import javax.persistence.*;
import java.util.Set;


@Entity
@Getter
@Setter
public class League {

    public enum LeagueState {
        PENDING, IN_PROGRESS, COMPLETED;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    
    private int playTime;
    private int numRounds;

    @Enumerated(EnumType.STRING)
    private LeagueState state;

    @OneToMany(mappedBy = "league")
    private Set<Round> round;
}