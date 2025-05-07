package jaumesitos.backend.demo.infrastructure.db.dbo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import jaumesitos.backend.demo.infrastructure.config.ClassificacioId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@IdClass(ClassificacioId.class)
@Table(name = "classificacio") // <-- This line fixes the issue
public class ParticipationDBO {
    @Id
    int leagueid;
    @Id
    int botid;
    int points;
    int matches;
    int wins;
    int draws;
    int losses;
    LocalDateTime inscription;
}
