package jaumesitos.backend.demo.infrastructure.res.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class EnfrentamentDTO {
    private String id;
    private String idBotLocal;
    private String idBotVisitant;
    private LocalDateTime date;
    private  String resultat;
}
