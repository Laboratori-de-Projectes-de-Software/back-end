package uib.lab.api.entity;

import lombok.*;
import javax.persistence.*;
import java.util.Set;


@Entity
@Getter
@Setter
public class Bot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String ideologia;

    private String url;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "LONGBLOB")
    private byte[] imagen;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "bot1")
    private Set<Game> gamesAsBot1;

    @OneToMany(mappedBy = "bot2")
    private Set<Game> gamesAsBot2;
}
