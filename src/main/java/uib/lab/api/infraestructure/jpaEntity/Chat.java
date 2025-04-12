package uib.lab.api.infraestructure.jpaEntity;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDateTime date;
    private String content;

    @ManyToOne
    @JoinColumn(name = "match_id")
    private Match match;

    //Me falta id del bot...
    //private int bot_id;
}
