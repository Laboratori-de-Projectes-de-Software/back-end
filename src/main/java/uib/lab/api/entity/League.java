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

    @OneToMany(mappedBy = "league")
    private Set<Round> round;
}
