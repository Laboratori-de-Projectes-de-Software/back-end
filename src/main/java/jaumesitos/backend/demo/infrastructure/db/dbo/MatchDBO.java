package jaumesitos.backend.demo.infrastructure.db.dbo;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
@Table(name = "enfrontament")
@Getter
@Setter
@NoArgsConstructor
public class MatchDBO {
    @Id
    private int id;

    @Column(name = "id_local")
    private String botIdLocal;

    @Column(name = "id_visitant")
    private String botIdVisitant;

    @Column(name = "data")
    private LocalDateTime date;

    @Column(name = "resultat")
    private String result;

    @Column(name = "id_lliga")
    private int leagueId;
}