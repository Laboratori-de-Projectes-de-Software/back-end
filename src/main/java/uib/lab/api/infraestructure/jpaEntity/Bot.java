package uib.lab.api.infraestructure.jpaEntity;

import lombok.*;
import javax.persistence.*;
import java.util.Set;


@Entity
@Getter
@Setter
public class Bot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String ideologia;

    private String description;

    private String url;

    private String imagen;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "bot1")
    private Set<Match> matchAsBot1;

    @OneToMany(mappedBy = "bot2")
    private Set<Match> matchAsBot2;

    @ManyToMany(mappedBy = "bots")
    private Set<League> leagues;

    public Bot(){

    }
}
