package jaumesitos.backend.demo.infrastructure.db.dbo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@Entity

public class LligaDBO {
    LocalDateTime data;
    @Id
    String nom;
    Boolean estat;

}
