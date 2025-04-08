package uib.lab.api.infraestructure.jpaEntity;

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
    private int id;

    private String name;
    private String urlImagen;
    private int playTime;
    private int numRounds;

    @Enumerated(EnumType.STRING)
    private LeagueState state;

    @OneToMany(mappedBy = "league")
    private Set<Round> rounds;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "league_bot",
            joinColumns = @JoinColumn(name = "league_id"),
            inverseJoinColumns = @JoinColumn(name = "bot_id")
    )
    private Set<Bot> bots;
}