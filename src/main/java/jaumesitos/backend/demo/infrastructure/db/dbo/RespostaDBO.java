package jaumesitos.backend.demo.infrastructure.db.dbo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "resposta")
@Getter
@Setter
@NoArgsConstructor
public class RespostaDBO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "text")
    private String argument;

    @Column(name = "temps")
    private LocalDateTime date;

    @Column(name = "id_enfrontament")
    private int matchId;

    @Column(name = "id_autor")
    private Integer authorId;
}
