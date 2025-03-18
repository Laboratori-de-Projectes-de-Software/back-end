package jaumesitos.backend.demo.domain;

import java.time.LocalDateTime;

public class Enfrentament {
    private String id;
    private String idBotLocal;
    private String idBotVisitant;
    private LocalDateTime date;
    private String resultat;

    public Enfrentament(String id, String idBotLocal, String idBotVisitant, LocalDateTime date, String resultat) {
        this.id = id;
        this.idBotLocal = idBotLocal;
        this.idBotVisitant = idBotVisitant;
        this.date = date;
        this.resultat = resultat;
    }
}
