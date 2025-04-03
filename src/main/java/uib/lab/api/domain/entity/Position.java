package uib.lab.api.domain.entity;

import lombok.*;
import javax.persistence.*;

@Entity
@Getter
@Setter
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int points;
    private int win;
    private int draw;
    private int lose;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
