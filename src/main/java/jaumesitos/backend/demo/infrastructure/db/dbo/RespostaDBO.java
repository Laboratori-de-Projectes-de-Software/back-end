package jaumesitos.backend.demo.infrastructure.db.dbo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "resposta")
public class RespostaDBO {
    @Id
    private String id;
    private String argument;
    private String date;
}
