package jaumesitos.backend.demo.infrastructure.db.dbo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class RespostaDBO {
    @Id
    private String id;
    private String argument;
    private String date;

    @ManyToOne
    @JoinColumn(name = "id_enfrontament") // o el nombre real de la columna en tu tabla
    private MatchDBO match;

}
