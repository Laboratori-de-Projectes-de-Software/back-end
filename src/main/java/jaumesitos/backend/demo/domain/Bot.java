package jaumesitos.backend.demo.domain;

import java.time.LocalDateTime;

public class Bot {
    private int id;
    private String name;
    private String modelIA;
    private LocalDateTime registrationDate;


    public Bot(String id, String name, String modelIA, LocalDateTime registrationDate) {
        this.id = id;
        this.name = name;
        this.modelIA = modelIA;
        this.registrationDate = registrationDate;
    }
}
