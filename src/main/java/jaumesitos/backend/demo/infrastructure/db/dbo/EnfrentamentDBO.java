package jaumesitos.backend.demo.infrastructure.db.dbo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "enfrentaments")
public class EnfrentamentDBO {
    @Id
    private String id;
    private String bot1Id;
    private String bot2Id;
    private LocalDateTime date;
    private String result;
}