package jaumesitos.backend.demo.domain;

import java.time.LocalDateTime;

public class Resposta {
    private String id;
    private String argument;
    private LocalDateTime date;

    public Resposta(String id, String argument, LocalDateTime date) {
        this.id = id;
        this.argument = argument;
        this.date = date;
    }
}
