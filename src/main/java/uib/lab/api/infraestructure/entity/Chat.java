package uib.lab.api.infraestructure.entity;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDateTime date;
    private String content;

    @ManyToOne
    @JoinColumn(name = "match_id")
    private Match match;
}
