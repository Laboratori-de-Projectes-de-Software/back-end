package uib.lab.api.entity;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;


@Entity
@Getter
@Setter
public class League {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    
    private LocalDateTime date;
    private int playTime;
    private int numRounds;

    @Enumerated(EnumType.STRING)
    private LeagueState state;

    @OneToMany(mappedBy = "league")
    private Set<Round> round;
}

enum LeagueState {
    PENDING, IN_PROGRESS, COMPLETED;
}