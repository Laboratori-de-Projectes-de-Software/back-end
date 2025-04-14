package jaumesitos.backend.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Match {
    private String id;
    private String idBotLocal;
    private String idBotVisitant;
    private LocalDateTime date;
    private String resultat;
}
