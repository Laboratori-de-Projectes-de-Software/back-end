package jaumesitos.backend.demo.infrastructure.db.dbo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class BotDBO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String description;
    private String urlImage;
    private String endpoint;
    private int wins;
    private int losses;
    private int draws;

    @Column(name = "propietari")
    private int ownerId;
}
