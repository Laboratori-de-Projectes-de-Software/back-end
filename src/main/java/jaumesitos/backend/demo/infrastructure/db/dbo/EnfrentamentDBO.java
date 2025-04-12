package jaumesitos.backend.demo.infrastructure.db.dbo;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "enfrontament")
@Getter
@Setter
@NoArgsConstructor
public class EnfrentamentDBO {

    @Id
    private int id;

    @Column(name = "id_local")
    private String bot1Id;

    @Column(name = "id_visitant")
    private String bot2Id;

    @Column(name = "data")
    private LocalDateTime date;

    @Column(name = "resultat")
    private String result;

    @Column(name = "id_lliga")
    private int leagueId;
}