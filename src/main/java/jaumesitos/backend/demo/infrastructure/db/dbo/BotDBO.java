package jaumesitos.backend.demo.infrastructure.db.dbo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "bots")
public class BotDBO {
    @Id
    private String id;
    private String name;
    private String modelIA;
    private LocalDate registrationDate;
}
